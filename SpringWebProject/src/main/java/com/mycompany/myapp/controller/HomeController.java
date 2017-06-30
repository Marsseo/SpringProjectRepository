package com.mycompany.myapp.controller;

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

	@RequestMapping(value = "/jm", method = RequestMethod.GET)
	public String home(Model model) {
		CoapClient coapClient = new CoapClient();
		CoapResponse coapResponse = null;
		JSONObject jsonObject = null;
		String json = null;
		/*
		 * thermistorsensor
		 */
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
		/*
		 * photoresistorsensor
		 */
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
		/*
		 * gassensor
		 */
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
		/*
		 * trackingsensor
		 */
		jsonObject = new JSONObject();
		jsonObject.put("command", "status");
		json = jsonObject.toString();
		
		coapClient.setURI("coap://"+ipAddress+"/trackingsensor");
		coapResponse = coapClient.post(json, MediaTypeRegistry.APPLICATION_JSON);
		json = coapResponse.getResponseText();
		
		jsonObject = new JSONObject(json);
		String tracking = jsonObject.getString("tracking");

		model.addAttribute("tracking", tracking);

		return "charttest";

	}
}
