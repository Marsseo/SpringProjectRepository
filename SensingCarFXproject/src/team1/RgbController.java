package team1;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapResponse;
import org.eclipse.californium.core.coap.MediaTypeRegistry;
import org.json.JSONObject;

public class RgbController implements Initializable {

    @FXML
    Pane pane;
    @FXML
    Slider sliderR;
    @FXML
    Slider sliderG;
    @FXML
    Slider sliderB;
    @FXML
    private Button Red;
    @FXML
    private Button Green;
    @FXML
    private Button blue;
    @FXML
    private Button Send;

// 공통으로 사용하는 필드 값 정리
    private String ipAddress = "192.168.3.48";
    private CoapClient coapClient;
    private CoapResponse coapResponse;
    private JSONObject jsonObject;
    private String json;

    int redValue;
    int greenValue;
    int blueValue;

    public void initialize(URL url, ResourceBundle rb) {
        getState();
        //cambia el color de fondo del objeto Pane
        pane.setBackground(
                //la clase Background crea un fondo para agregar al Pane
                new Background(
                        //la clase BackgroundFill permiete crear un relleno 
                        new BackgroundFill(
                                Color.rgb(//Color RGB con los valores iniciales de los slider
                                        (int) sliderR.getValue(),
                                        (int) sliderG.getValue(),
                                        (int) sliderB.getValue()
                                ), CornerRadii.EMPTY, Insets.EMPTY)));

        sliderR.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> ov,
                    Number old_val, Number new_val) {
                pane.setBackground(new Background(new BackgroundFill(
                        Color.rgb(
                                new_val.intValue(),
                                (int) sliderG.getValue(),
                                (int) sliderB.getValue()
                        ), CornerRadii.EMPTY, Insets.EMPTY)));
                int nowRedValue= new_val.intValue();
                redValue=nowRedValue; 
                jsonObject = new JSONObject();
                jsonObject.put("command", "change");
                jsonObject.put("red", String.valueOf(nowRedValue));
                jsonObject.put("green", String.valueOf(greenValue));
                jsonObject.put("blue", String.valueOf(blueValue));
                json = jsonObject.toString();

                coapClient = new CoapClient();
                coapClient.setURI("coap://" + ipAddress + "/rgbled");
                coapResponse = coapClient.post(json, MediaTypeRegistry.APPLICATION_JSON);
                coapClient.shutdown();
            }
        });

        sliderG.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> ov,
                    Number old_val, Number new_val) {
                pane.setBackground(new Background(new BackgroundFill(
                        Color.rgb(
                                (int) sliderR.getValue(),
                                new_val.intValue(),
                                (int) sliderB.getValue()
                        ), CornerRadii.EMPTY, Insets.EMPTY)));
                  int nowGreenValue= new_val.intValue();
                greenValue=nowGreenValue; 
                jsonObject = new JSONObject();
                jsonObject.put("command", "change");
                jsonObject.put("red", String.valueOf(redValue));
                jsonObject.put("green", String.valueOf(nowGreenValue));
                jsonObject.put("blue", String.valueOf(blueValue));
                json = jsonObject.toString();

                coapClient = new CoapClient();
                coapClient.setURI("coap://" + ipAddress + "/rgbled");
                coapResponse = coapClient.post(json, MediaTypeRegistry.APPLICATION_JSON);
                coapClient.shutdown();
            }
            
        });

        sliderB.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> ov,
                    Number old_val, Number new_val) {
                pane.setBackground(new Background(new BackgroundFill(
                        Color.rgb(
                                (int) sliderR.getValue(),
                                (int) sliderG.getValue(),
                                new_val.intValue()
                        ), CornerRadii.EMPTY, Insets.EMPTY)));
                      int nowBlueValue= new_val.intValue();
                blueValue=nowBlueValue; 
                jsonObject = new JSONObject();
                jsonObject.put("command", "change");
                jsonObject.put("red", String.valueOf(redValue));
                jsonObject.put("green", String.valueOf(greenValue));
                jsonObject.put("blue", String.valueOf(nowBlueValue));
                json = jsonObject.toString();

                coapClient = new CoapClient();
                coapClient.setURI("coap://" + ipAddress + "/rgbled");
                coapResponse = coapClient.post(json, MediaTypeRegistry.APPLICATION_JSON);
                coapClient.shutdown();
            }
            
        });
    }

    @FXML
    private void handleBtnRed(ActionEvent event) {
        sliderR.setValue(255);
        sliderG.setValue(0);
        sliderB.setValue(0);
    }

    @FXML
    private void handleBtnGreen(ActionEvent event) {
        sliderR.setValue(0);
        sliderG.setValue(255);
        sliderB.setValue(0);
    }

    @FXML
    private void handleBtnBlue(ActionEvent event) {
        sliderR.setValue(0);
        sliderG.setValue(0);
        sliderB.setValue(255);
    }

   private void getState(){
        jsonObject = new JSONObject();
        jsonObject.put("command", "status");
        json = jsonObject.toString();

        coapClient = new CoapClient();
        coapClient.setURI("coap://" + ipAddress + "/rgbled");
        coapResponse = coapClient.post(json, MediaTypeRegistry.APPLICATION_JSON);
        json = coapResponse.getResponseText();

        jsonObject = new JSONObject(json);
        sliderR.setValue(jsonObject.getDouble("red"));
        sliderG.setValue(jsonObject.getDouble("green"));
        sliderB.setValue(jsonObject.getDouble("blue"));
        
        
    }
}
