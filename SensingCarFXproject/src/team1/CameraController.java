package team1;

import hardware.camera.ViewerCanvas;
import java.net.URL;
import java.util.ResourceBundle;


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;

import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.scene.control.Slider;

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
    private String ipAddress =AppMain.ipAddress;
    private CoapClient coapClient;
    private CoapResponse coapResponse;
    private JSONObject jsonObject;
    private String json;
    private int h=90;
    private int v=45;
   static int counter=0;
   

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        ViewerCanvas viewer= new ViewerCanvas(600,350);
        try {
            viewer.setCurrentURL(new URL("http://"+ipAddress+":50001/?action=stream"));
        } catch (MalformedURLException ex) {
            Logger.getLogger(CameraController.class.getName()).log(Level.SEVERE, null, ex);
        }
       bpWebCamPaneHolder.getChildren().add(viewer);
       

        hSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                int hAngle=180-newValue.intValue();
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
               ++counter;


    }
    

        
        
    }
