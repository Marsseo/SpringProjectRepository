package team1;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import org.json.JSONObject;


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
	private Label speed;
	@FXML
	private Label clock;
	@FXML
	private Label Tracking;
	@FXML
	private Label direction;
	
	private CoapClient4Car client;
	

	public HomeController() {
		client = new CoapClient4Car();
	}
	
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		
		final Map<String, String> sensingMap = new HashMap<>(); 
		
		Thread threadclk = new Thread() {
            @Override
            public void run() {
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                while (true) {
                    String strTime = sdf.format(new Date());
                    Platform.runLater(() -> {
                        clock.setText(strTime);
                        System.gc();
                    });
                    try {
                        Thread.sleep(100);
                    } catch (Exception e) {
                    }
                }

            }
        };
        threadclk.setDaemon(true);
        threadclk.start();
		
		Thread thread = new Thread(){
			@Override
			public void run() {
				while(true){
					try {	Thread.sleep(1000);} catch (InterruptedException ex) {	Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);}

					sensingMap.put("temperature",String.valueOf(client.curObserveState("thermistorsensor")));
					sensingMap.put("photoresistor",String.valueOf(client.curObserveState("photoresistorsensor")));
					sensingMap.put("gas",String.valueOf(client.curObserveState("gassensor")));
					sensingMap.put("distance",String.valueOf(client.curObserveState("ultrasonicsensor")));
					sensingMap.put("tacking", client.trackingObserve());
					String json = client.backTireState();
					JSONObject jsonObject = new JSONObject(json);
					sensingMap.put("speed", jsonObject.getString("speed"));
					sensingMap.put("direction", jsonObject.getString("direction"));

					Platform.runLater(()->{
						temperagture.setText(sensingMap.get("temperature"));

						photoresistor.setText(sensingMap.get("photoresistor"));

						gas.setText(sensingMap.get("gas"));

						distance.setText(sensingMap.get("distance"));

						Tracking.setText(sensingMap.get("tacking"));

						speed.setText(sensingMap.get("speed"));

						direction.setText(sensingMap.get("direction"));
					});
				}
			}
		};
		thread.start();	
		
	}	
	
}
