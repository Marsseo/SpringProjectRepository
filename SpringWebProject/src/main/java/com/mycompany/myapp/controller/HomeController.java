package com.mycompany.myapp.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapResponse;
import org.eclipse.californium.core.coap.MediaTypeRegistry;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	private String ipAddress = IpAdress.getIpAddress();

	@RequestMapping(value = "/project", method = RequestMethod.GET)
	public String home(Model model) {
		CoapClient coapClient = new CoapClient();
		CoapResponse coapResponse = null;
		JSONObject jsonObject = null;
		String json = null;
		
//		 		thermistorsensor
		 
		jsonObject = new JSONObject();
		jsonObject.put("command", "status");
		json = jsonObject.toString();

		coapClient.setURI("coap://"+ipAddress+"/thermistorsensor");
		coapResponse = coapClient.post(json, MediaTypeRegistry.APPLICATION_JSON);
		json = coapResponse.getResponseText();

		jsonObject = new JSONObject(json);
		double doubleT = Double.parseDouble(jsonObject.getString("temperature"));
		int temperature = (int)doubleT;
		model.addAttribute("temperature", temperature);
		
//		 		photoresistorsensor
		 
		jsonObject = new JSONObject();
		jsonObject.put("command", "status");
		json = jsonObject.toString();
		
		coapClient.setURI("coap://"+ipAddress+"/photoresistorsensor");
		coapResponse = coapClient.post(json, MediaTypeRegistry.APPLICATION_JSON);
		json = coapResponse.getResponseText();
		
		jsonObject = new JSONObject(json);
		doubleT = Double.parseDouble(jsonObject.getString("photoresistor"));
		int photoresistor = (int)doubleT;
		
		String strValue = "";
		if(photoresistor < 20 ){
			strValue ="아주밝음";
		}else if(photoresistor < 100 ){
			strValue ="밝음";
		}else if(photoresistor < 150 ){
			strValue ="보통";
		}else {
			strValue ="어두움";
		}
		model.addAttribute("photoresistor", photoresistor);
		model.addAttribute("photoresistorStr", strValue);
		
//		  			gassensor
		 
		jsonObject = new JSONObject();
		jsonObject.put("command", "status");
		json = jsonObject.toString();
		
		coapClient.setURI("coap://"+ipAddress+"/gassensor");
		coapResponse = coapClient.post(json, MediaTypeRegistry.APPLICATION_JSON);
		json = coapResponse.getResponseText();
		
		jsonObject = new JSONObject(json);
		doubleT = Double.parseDouble(jsonObject.getString("gas"));
		int gas = (int)doubleT;
		
		if(gas < 40 ){
			strValue ="아주좋음";
		}else if(gas < 80 ){
			strValue ="보통";
		}else if(gas < 150 ){
			strValue ="가스검출";
		}else {
			strValue ="가스심각";
		}
		model.addAttribute("gas", gas);
		model.addAttribute("gasStr", strValue);
		
//		 		trackingsensor
		 
		jsonObject = new JSONObject();
		jsonObject.put("command", "status");
		json = jsonObject.toString();
		
		coapClient.setURI("coap://"+ipAddress+"/trackingsensor");
		coapResponse = coapClient.post(json, MediaTypeRegistry.APPLICATION_JSON);
		json = coapResponse.getResponseText();
		
		jsonObject = new JSONObject(json);
		String tracking = jsonObject.getString("tracking");

		model.addAttribute("tracking", tracking);
		
//			lcd
		
		jsonObject = new JSONObject();
		jsonObject.put("command", "status");
		json = jsonObject.toString();

		coapClient.setURI("coap://"+ipAddress+"/lcd");
		coapResponse = coapClient.post(json, MediaTypeRegistry.APPLICATION_JSON);
		json = coapResponse.getResponseText();

		jsonObject = new JSONObject(json);
		model.addAttribute("lcdline0", jsonObject.getString("line0"));
		model.addAttribute("lcdline1", jsonObject.getString("line1"));
		
		
		// -camera-------------------------------------------------
		jsonObject = new JSONObject();
		logger.info("");
		jsonObject.put("command", "status");
		json = jsonObject.toString();
		coapClient.setURI("coap://"+ipAddress+"/camera");
		coapResponse = coapClient.post(json, MediaTypeRegistry.APPLICATION_JSON);
		json = coapResponse.getResponseText();
		jsonObject = new JSONObject(json);
		model.addAttribute("leftright", jsonObject.getString("leftright"));
		model.addAttribute("updown", jsonObject.getString("updown"));
		
		//rgb -------------------------------------------------------
		jsonObject = new JSONObject();
		jsonObject.put("command", "status");
		json = jsonObject.toString();
		coapClient.setURI("coap://"+ipAddress+"/rgbled");
		coapResponse = coapClient.post(json, MediaTypeRegistry.APPLICATION_JSON);
		json = coapResponse.getResponseText();
		jsonObject = new JSONObject(json);
		model.addAttribute("red", jsonObject.getString("red"));
		model.addAttribute("green", jsonObject.getString("green"));
		model.addAttribute("blue", jsonObject.getString("blue"));
		
		//초음파---------------------------------------------------------------
		
		jsonObject = new JSONObject();
		jsonObject.put("command", "status");
		json = jsonObject.toString();
		coapClient.setURI("coap://"+ipAddress+"/ultrasonicsensor");
		coapResponse = coapClient.post(json, MediaTypeRegistry.APPLICATION_JSON);
		json = coapResponse.getResponseText();
		jsonObject = new JSONObject(json);		
		model.addAttribute("angle", jsonObject.getString("angle"));
		model.addAttribute("distance", jsonObject.getString("distance"));
		model.addAttribute("change","null");
		
		//레이저---------------------------------------------------------------
		jsonObject = new JSONObject();
		jsonObject.put("command", "status");
		json = jsonObject.toString();
		coapClient.setURI("coap://"+ipAddress+"/laseremitter");
		coapResponse = coapClient.post(json, MediaTypeRegistry.APPLICATION_JSON);
		json = coapResponse.getResponseText();
		jsonObject = new JSONObject(json);
		model.addAttribute("laseremitterStatus", jsonObject.getString("status"));
		
		//부저---------------------------------------------------------------
		jsonObject = new JSONObject();
		jsonObject.put("command", "status");
		json = jsonObject.toString();
		coapClient.setURI("coap://"+ipAddress+"/buzzer");
		coapResponse = coapClient.post(json, MediaTypeRegistry.APPLICATION_JSON);
		json = coapResponse.getResponseText();
		jsonObject = new JSONObject(json);
		model.addAttribute("buzzerStatus", jsonObject.getString("status"));
		
		//----------------------------------------------
		// 앞바퀴
		jsonObject = new JSONObject();
		jsonObject.put("command", "status");
		json = jsonObject.toString();
		coapClient = new CoapClient();
		coapClient.setURI("coap://"+ipAddress+"/fronttire");
		coapResponse = coapClient.post(json, MediaTypeRegistry.APPLICATION_JSON);
		json = coapResponse.getResponseText();
		jsonObject = new JSONObject(json);
		model.addAttribute("fronttireAngle", jsonObject.getString("angle"));	


//--------------------------------------------------------------------------------------------------
		// 뒷바퀴
		jsonObject = new JSONObject();
		jsonObject.put("command", "status");
		json = jsonObject.toString();
		coapClient.setURI("coap://"+ipAddress+"/backtire");
		coapResponse = coapClient.post(json, MediaTypeRegistry.APPLICATION_JSON);
		json = coapResponse.getResponseText();
		jsonObject = new JSONObject(json);
		model.addAttribute("backtireDirection", jsonObject.getString("direction"));	
		model.addAttribute("backtireSpeed", jsonObject.getString("speed"));
		
//--------------------------------------------------------------------------------------------------

		model.addAttribute("cameraUrl", "http://"+"192.168.3.50"+":50001?action=stream");
		coapClient.shutdown();
		return "charttest";

	}
	
//	 			lcd
	
	@RequestMapping("/lcd")
	public void lcd(String command, String line0, String line1, HttpServletResponse response) throws IOException {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("command", command);
		jsonObject.put("line0", line0);
		jsonObject.put("line1", line1);
		String reqJson = jsonObject.toString();

		CoapClient coapClient = new CoapClient();
		coapClient.setURI("coap://"+ipAddress+"/lcd");
		CoapResponse coapResponse = coapClient.post(reqJson, MediaTypeRegistry.APPLICATION_JSON);
		String resJson = coapResponse.getResponseText();
		coapClient.shutdown();
		response.setContentType("application/json; charset=UTF-8");

		PrintWriter pw = response.getWriter();
		pw.write(resJson);
		pw.flush();
		pw.close();
	}
	
	@RequestMapping("/camera")
	public void camera(String command, String leftright, String updown, HttpServletResponse response)
			throws IOException {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("command", command);
		jsonObject.put("leftright", leftright);
		jsonObject.put("updown", updown);
		String reqJson = jsonObject.toString();
		
		CoapClient coapClient = new CoapClient();
		coapClient.setURI("coap://"+ipAddress+"/camera");
		CoapResponse coapResponse = coapClient.post(reqJson, MediaTypeRegistry.APPLICATION_JSON);
		String resJson = coapResponse.getResponseText();
		coapClient.shutdown();
		
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter pw = response.getWriter();
		pw.write(resJson);
		pw.flush();
		pw.close();
	}
	
	@RequestMapping("/rgbled")
	public void rgbled(String command, String red, String green, String blue,
			HttpServletResponse response)
			throws IOException {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("command", command);
		jsonObject.put("red", red);
		jsonObject.put("green", green);
		jsonObject.put("blue", blue);
		String reqJson = jsonObject.toString();
		
		CoapClient coapClient = new CoapClient();
		coapClient.setURI("coap://"+ipAddress+"/rgbled");
		CoapResponse coapResponse = coapClient.post(reqJson,
				MediaTypeRegistry.APPLICATION_JSON);
		String resJson = coapResponse.getResponseText();
		coapClient.shutdown();
		
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter pw = response.getWriter();
		pw.write(resJson);
		pw.flush();
		pw.close();
	}
	
	
	@RequestMapping("/ultrasonicsensor")
	public void ultrasonicsensor(String command, String angle,HttpServletResponse response) throws IOException {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("command", command);
		jsonObject.put("angle", angle);
		String reqJson = jsonObject.toString();
		
		CoapClient coapClient = new CoapClient();
		coapClient.setURI("coap://"+ipAddress+"/ultrasonicsensor");   //coap에 등록해놓은 리소스 이름
		CoapResponse coapResponse = coapClient.post(reqJson, MediaTypeRegistry.APPLICATION_JSON);
		String resJson = coapResponse.getResponseText();
		coapClient.shutdown();
		
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter pw = response.getWriter();
		pw.write(resJson);
		pw.flush();
		pw.close();
	}
	
	@RequestMapping("/laseremitter")
	public void rgbled(String command, String status, HttpServletResponse response) throws IOException {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("command", command);
		jsonObject.put("status", status);
		String reqJson = jsonObject.toString();
		
		CoapClient coapClient = new CoapClient();
		coapClient.setURI("coap://"+ipAddress+"/laseremitter");
		CoapResponse coapResponse = coapClient.post(reqJson, MediaTypeRegistry.APPLICATION_JSON);
		String resJson = coapResponse.getResponseText();
		coapClient.shutdown();
		
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter pw = response.getWriter();
		pw.write(resJson);
		pw.flush();
		pw.close();
	}
	
	@RequestMapping("/buzzer")
	public void buzzer(String command, String status, HttpServletResponse response) throws IOException {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("command", command);
		jsonObject.put("status", status);
		String reqJson = jsonObject.toString();
		
		CoapClient coapClient = new CoapClient();
		coapClient.setURI("coap://"+ipAddress+"/buzzer");
		CoapResponse coapResponse = coapClient.post(reqJson, MediaTypeRegistry.APPLICATION_JSON);
		String resJson = coapResponse.getResponseText();
		coapClient.shutdown();
		
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter pw = response.getWriter();
		pw.write(resJson);
		pw.flush();
		pw.close();
	}
	
	// 앞바퀴와 뒷바퀴 제어
	@RequestMapping("/fronttire")
	public void fronttire(String command, String angle, HttpServletResponse response) throws IOException {
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("command", command);
		jsonObject.put("angle", angle);
		String json = jsonObject.toString();
		
		CoapClient coapClient = new CoapClient();
		coapClient.setURI("coap://"+ipAddress+"/fronttire");
		CoapResponse coapResponse = coapClient.post(json, MediaTypeRegistry.APPLICATION_JSON);
		json = coapResponse.getResponseText();
		coapClient.shutdown();
		
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter pwr = response.getWriter();
		pwr.write(json);
		pwr.flush();
		pwr.close();
		
	}
	
	@RequestMapping("/backtire")
	public void backtire(String command, String direction, String speed, HttpServletResponse response, Model model) throws IOException {
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("command", command);
		jsonObject.put("direction", direction);
		jsonObject.put("speed", speed);
		String json = jsonObject.toString();
		
		CoapClient coapClient = new CoapClient();
		coapClient.setURI("coap://"+ipAddress+"/backtire");
		CoapResponse coapResponse = coapClient.post(json, MediaTypeRegistry.APPLICATION_JSON);
		json = coapResponse.getResponseText();
		jsonObject = new JSONObject(json);
		model.addAttribute("backtireDirection", jsonObject.getString("direction"));	
		model.addAttribute("backtireSpeed", jsonObject.getString("speed"));
		coapClient.shutdown();
		
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter pwr = response.getWriter();
		pwr.write(json);
		pwr.flush();
		pwr.close();
		
	}
}
