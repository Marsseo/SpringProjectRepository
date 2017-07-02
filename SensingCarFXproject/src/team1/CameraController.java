package team1;

import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;

import com.github.sarxos.webcam.Webcam;

import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.ds.ipcam.IpCamDeviceRegistry;
import com.github.sarxos.webcam.ds.ipcam.IpCamDriver;
import com.github.sarxos.webcam.ds.ipcam.IpCamMode;
import java.awt.Graphics2D;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingNode;
import javafx.scene.control.Slider;
import javafx.scene.text.Font;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapResponse;
import org.eclipse.californium.core.coap.MediaTypeRegistry;
import org.json.JSONObject;

public class CameraController implements Initializable {

    @FXML
    BorderPane bpWebCamPaneHolder;
    @FXML
    private FlowPane fpBottomPane;
    @FXML
    private Slider hSlider;
    @FXML
    private Slider vSlider;

    // 공통으로 사용하는 필드 값 정리
    private String ipAddress = "192.168.3.48";
    private CoapClient coapClient;
    private CoapResponse coapResponse;
    private JSONObject jsonObject;
    private String json;
    private int h=90;
    private int v=45;

   

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        Webcam.setDriver(new IpCamDriver());
        try {
            IpCamDeviceRegistry.register("RPi", "http://192.168.3.48:50001/?action=stream", IpCamMode.PUSH);
        } catch (MalformedURLException ex) {
            Logger.getLogger(CameraController.class.getName()).log(Level.SEVERE, null, ex);
        }

        WebcamPanel panel = new WebcamPanel(Webcam.getWebcams().get(0));
        panel.setFPSLimit(1);
        SwingNode swingNode = new SwingNode();
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                swingNode.setContent(panel);
            }
        });
        
        bpWebCamPaneHolder.setCenter(swingNode);
        
        getState();
        
        hSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                int hAngle=newValue.intValue();
                h=hAngle;
                System.out.println(h);
                
                jsonObject= new JSONObject();
                jsonObject.put("command", "change");
                jsonObject.put("leftright", String.valueOf(hAngle));
                jsonObject.put("updown", String.valueOf(v));
                json=jsonObject.toString();
                
                coapClient= new CoapClient();
                coapClient.setURI("coap://"+ipAddress+"/camera");
                coapResponse= coapClient.post(json, MediaTypeRegistry.APPLICATION_JSON);
                coapClient.shutdown();
            }
        });
        
               vSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                int vAngle=newValue.intValue();
                v=vAngle;
                 System.out.println(v);
                jsonObject= new JSONObject();
                jsonObject.put("command", "change");
                jsonObject.put("leftright",String.valueOf(h));
                jsonObject.put("updown", String.valueOf(vAngle));
                json=jsonObject.toString();
                
                coapClient= new CoapClient();
                coapClient.setURI("coap://"+ipAddress+"/camera");
                coapResponse= coapClient.post(json, MediaTypeRegistry.APPLICATION_JSON);
                coapClient.shutdown();
            }
        });



    }
    
    private void getState(){
        jsonObject = new JSONObject();
        jsonObject.put("command", "status");
        json = jsonObject.toString();

        coapClient = new CoapClient();
        coapClient.setURI("coap://" + ipAddress + "/camera");
        coapResponse = coapClient.post(json, MediaTypeRegistry.APPLICATION_JSON);
        json = coapResponse.getResponseText();

        jsonObject = new JSONObject(json);
        hSlider.setValue(jsonObject.getDouble("leftright"));
        vSlider.setValue(jsonObject.getDouble("updown"));
        
        
        
    }



}
