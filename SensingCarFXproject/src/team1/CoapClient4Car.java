package team1;

import java.util.List;
import java.util.Vector;
import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapHandler;
import org.eclipse.californium.core.CoapObserveRelation;
import org.eclipse.californium.core.CoapResponse;
import org.eclipse.californium.core.coap.MediaTypeRegistry;
import org.json.JSONObject;


public class CoapClient4Car {
	
	// Coap 클라이언트와 관찰기능 추가
	private CoapClient coapClient;
	private CoapObserveRelation coapObserveRelation;
	// 아이피 세팅 ( 이 아이피를 바꾸면 자신의 차에서 작동함)
	private String ipAddress =AppMain.ipAddress;
	
	private CoapResponse coapResponse;
	private JSONObject jsonObject;
	private String json;
	
	private List<String> list = new Vector();	
		
	public void shutdown(){
		coapClient.shutdown();
	}	
	
	/*
	각각의 센서의 따른 포스트와 관찰기능을 메소드로 정의
	혹시 작동 안할 경우 setURI의 주소를 확인 바람
	아니면 json에서 들어가는 값의 오타가 있는지 확인
	
	리턴값은 응답에 대한 json을 스트링 타입으로 리턴하여
	객체의 메소드를 호출한 측에서 jsonObect를 이용하여
	원하는 값을 키로 찾을 수 있음
	
	*/
	
	public String backTirePost(String direction, String speed){
		
		jsonObject = new JSONObject();
		jsonObject.put("command", "change");
		jsonObject.put("direction", direction);
		jsonObject.put("speed", speed);
		json = jsonObject.toString();
		
		coapClient = new CoapClient();
		coapClient.setURI("coap://" + ipAddress + "/backtire");
		coapResponse = coapClient.post(json, MediaTypeRegistry.APPLICATION_JSON);
		json = coapResponse.getResponseText();
		shutdown();
		
		return json;
	}
	public String backTireState(){
		
		jsonObject = new JSONObject();
		jsonObject.put("command", "status");
		json = jsonObject.toString();
		
		coapClient = new CoapClient();
		coapClient.setURI("coap://" + ipAddress + "/backtire");
		coapResponse = coapClient.post(json, MediaTypeRegistry.APPLICATION_JSON);
		json = coapResponse.getResponseText();
		shutdown();
		
		return json;
	}
	public String frontTirePost(String angle){
		
		jsonObject = new JSONObject();
		jsonObject.put("command", "change");
		jsonObject.put("angle", angle);
		json = jsonObject.toString();
		
		coapClient = new CoapClient();
		coapClient.setURI("coap://" + ipAddress + "/fronttire");
		coapResponse = coapClient.post(json, MediaTypeRegistry.APPLICATION_JSON);
		json = coapResponse.getResponseText();
		shutdown();
		
		return json;
	}
	public String cameraPost(String leftright, String updown){
		
		jsonObject = new JSONObject();
		jsonObject.put("command", "change");
		jsonObject.put("leftright", leftright);
		jsonObject.put("updown", updown);
		json = jsonObject.toString();
		
		coapClient = new CoapClient();
		coapClient.setURI("coap://" + ipAddress + "/camera");
		coapResponse = coapClient.post(json, MediaTypeRegistry.APPLICATION_JSON);
		json = coapResponse.getResponseText();
		shutdown();
		
		return json;
	}
	
	public String buzzerPost(String status){
		
		jsonObject = new JSONObject();
		jsonObject.put("command", "change");
		jsonObject.put("status", status);
		json = jsonObject.toString();
		
		coapClient = new CoapClient();
		coapClient.setURI("coap://" + ipAddress + "/buzzer");
		coapResponse = coapClient.post(json, MediaTypeRegistry.APPLICATION_JSON);
		json = coapResponse.getResponseText();
		shutdown();

		return json;
	}	
	public String laserPost(String status){
		
		jsonObject = new JSONObject();
		jsonObject.put("command", "change");
		jsonObject.put("status", status);
		json = jsonObject.toString();
		
		coapClient = new CoapClient();
		coapClient.setURI("coap://" + ipAddress + "/laseremitter");
		coapResponse = coapClient.post(json, MediaTypeRegistry.APPLICATION_JSON);
		json = coapResponse.getResponseText();
		shutdown();
		
		return json;
	}
	public String RGBPost(String red, String green, String blue){
		
		jsonObject = new JSONObject();
		jsonObject.put("command", "change");
		jsonObject.put("red", red);
		jsonObject.put("green", green);
		jsonObject.put("blue", blue);
		json = jsonObject.toString();
		
		coapClient = new CoapClient();
		coapClient.setURI("coap://" + ipAddress + "/rgbled");
		coapResponse = coapClient.post(json, MediaTypeRegistry.APPLICATION_JSON);
		json = coapResponse.getResponseText();
		shutdown();
		
		return json;
	}
	public String LCDPost(String line0, String line1){
		
		jsonObject = new JSONObject();
		jsonObject.put("command", "change");
		jsonObject.put("line0", line0);
		jsonObject.put("line1", line1);
		json = jsonObject.toString();
		
		coapClient = new CoapClient();
		coapClient.setURI("coap://" + ipAddress + "/lcd");
		coapResponse = coapClient.post(json, MediaTypeRegistry.APPLICATION_JSON);
		json = coapResponse.getResponseText();
		shutdown();
		
		return json;
	}
	
	public String UltrasonicPost(String angle){
		
		jsonObject = new JSONObject();
		jsonObject.put("command", "change");
		jsonObject.put("angle", angle);
		json = jsonObject.toString();
		
		coapClient = new CoapClient();
		coapClient.setURI("coap://" + ipAddress + "/ultrasonicsensor");
		coapResponse = coapClient.post(json, MediaTypeRegistry.APPLICATION_JSON);
		json = coapResponse.getResponseText();
		shutdown();
		
		return null;
	}
	
	public int UltrasonicObserve(){
		
		coapClient = new CoapClient();
		coapClient.setURI("coap://" + ipAddress + "/ultrasonicsensor");
		coapObserveRelation = coapClient.observe(new CoapHandler() {
			@Override
			public void onLoad(CoapResponse response) {
				
				String json = response.getResponseText();
				JSONObject jsonObject = new JSONObject(json);
				int intD = Integer.parseInt(jsonObject.getString("distance"));

			}

			@Override
			public void onError() {
				
			}
		});
		coapObserveRelation.proactiveCancel();
		shutdown();
		
		return 0;
	}
	public int thermistorObserve(){
		
		coapClient = new CoapClient();
		coapClient.setURI("coap://" + ipAddress + "/thermistorsensor");
		coapObserveRelation = coapClient.observe(new CoapHandler() {
			@Override
			public void onLoad(CoapResponse response) {
				
				String json = response.getResponseText();
				JSONObject jsonObject = new JSONObject(json);
				double doubleT = Double.parseDouble(jsonObject.getString("temperature"));
				int temperature = (int)doubleT;
			}

			@Override
			public void onError() {
				
			}
		});
		coapObserveRelation.proactiveCancel();
		shutdown();
		
		return 0;
	}
	public int photoresistorObserve(){
		
		coapClient = new CoapClient();
		coapClient.setURI("coap://" + ipAddress + "/photoresistorsensor");
		coapObserveRelation = coapClient.observe(new CoapHandler() {
			@Override
			public void onLoad(CoapResponse response) {
				
				String json = response.getResponseText();
				JSONObject jsonObject = new JSONObject(json);
				double doubleT = Double.parseDouble(jsonObject.getString("photoresistor"));
				int light = (int)doubleT;
			}

			@Override
			public void onError() {
				
			}
		});
		coapObserveRelation.proactiveCancel();
		shutdown();
		
		return 0;
	}
	public int gasObserve(){
		
		final int gasstate;
		coapClient = new CoapClient();
		coapClient.setURI("coap://" + ipAddress + "/gassensor");
		coapObserveRelation = coapClient.observe(new CoapHandler() {
			@Override
			public void onLoad(CoapResponse response) {
				
				String json = response.getResponseText();
				JSONObject jsonObject = new JSONObject(json);
				double doubleT = Double.parseDouble(jsonObject.getString("gas"));
				int gas = (int)doubleT;
				
			}

			@Override
			public void onError() {
				
			}
		});
		coapObserveRelation.proactiveCancel();
		shutdown();
		
		return 0;
	}
	public String trackingObserve(){
					
		coapClient = new CoapClient();
		coapClient.setURI("coap://" + ipAddress + "/trackingsensor");
		coapObserveRelation = coapClient.observe(new CoapHandler() {
			@Override
			public void onLoad(CoapResponse response) {
				
				String json = response.getResponseText();
				JSONObject jsonObject = new JSONObject(json);
				String curColor = jsonObject.getString("tracking");
				list.add(curColor);
				//color = curColor;
			}

			@Override
			public void onError() {
				
			}
		});
		coapObserveRelation.proactiveCancel();
		shutdown();
		
		return null;
	}
	
	// 현재 상태를 읽기 위한 메소드
	public String curState(String resourceName){
		jsonObject = new JSONObject();
		jsonObject.put("command", "status");
		json = jsonObject.toString();

		coapClient = new CoapClient();
		coapClient.setURI("coap://" + ipAddress + "/"+resourceName);
		coapResponse = coapClient.post(json, MediaTypeRegistry.APPLICATION_JSON);
		json = coapResponse.getResponseText();
		
		return json;
	}
	
	public int curObserveState(String resourceName){
		jsonObject = new JSONObject();
		jsonObject.put("command", "status");
		json = jsonObject.toString();

		coapClient = new CoapClient();
		coapClient.setURI("coap://" + ipAddress + "/"+resourceName);
		coapResponse = coapClient.post(json, MediaTypeRegistry.APPLICATION_JSON);
		json = coapResponse.getResponseText();
		
		JSONObject jsonObject = new JSONObject(json);
		double tempValue;
		int stateValue=-1;
		
		switch(resourceName){
		
			case "thermistorsensor": tempValue = Double.parseDouble(jsonObject.getString("temperature"));
							  stateValue = (int)tempValue;
							  break;
			case "photoresistorsensor":	tempValue = Double.parseDouble(jsonObject.getString("photoresistor"));
								stateValue = (int)tempValue;
								break;
			case "gassensor": tempValue = Double.parseDouble(jsonObject.getString("gas"));
						 stateValue = (int)tempValue;
						 break;
			case "ultrasonicsensor": stateValue = Integer.parseInt(jsonObject.getString("distance"));
							  break;
			default: stateValue = 0;
				   break;
		}
		
		return stateValue;
	}
	
}
