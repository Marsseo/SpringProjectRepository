package team1;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;


public class HomeController implements Initializable {

	@FXML
	private Label temperagture;
	@FXML
	private Label photoresistor;
	@FXML
	private Label gas;
	@FXML
	private Label distance;
	@FXML
	private Label angle;
	@FXML
	private Label speed;
	
	private CoapClient4Car client;

	public HomeController() {
		client = new CoapClient4Car();
	}
	
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		
		temperagture.setText(String.valueOf(client.curObserveState("thermistorsensor")));
		
		photoresistor.setText(String.valueOf(client.curObserveState("photoresistorsensor")));
				
		gas.setText(String.valueOf(client.curObserveState("gassensor")));
		
		distance.setText(String.valueOf(client.curObserveState("ultrasonicsensor")));
		
		angle.setText("");
		
		speed.setText("");
	}	
	
}
