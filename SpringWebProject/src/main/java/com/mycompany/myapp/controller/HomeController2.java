package com.mycompany.myapp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;

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
public class HomeController2 {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController2.class);
	private String ipAddress=IpAdress.getIpAddress();
	
	@RequestMapping("/hs")
	public String home(Model model) {
		
		
		CoapClient coapClient=null;
		JSONObject jsonObject=null;
		String json = null;
		CoapResponse coapResponse = null;
		
		
		jsonObject = new JSONObject();
		jsonObject.put("command", "status");
		json = jsonObject.toString();
		coapClient = new CoapClient();
		coapClient.setURI("coap://"+ipAddress+"/fronttire");
		coapResponse = coapClient.post(json, MediaTypeRegistry.APPLICATION_JSON);
		//json = coapResponse.getResponseText();
		

		return "charttest2";
	}
	

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
	public void backtire(String command, String direction, String speed, HttpServletResponse response) throws IOException {
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("command", command);
		jsonObject.put("direction", direction);
		jsonObject.put("speed", speed);
		String json = jsonObject.toString();
		
		CoapClient coapClient = new CoapClient();
		coapClient.setURI("coap://"+ipAddress+"/backtire");
		CoapResponse coapResponse = coapClient.post(json, MediaTypeRegistry.APPLICATION_JSON);
		json = coapResponse.getResponseText();
		coapClient.shutdown();
		
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter pwr = response.getWriter();
		pwr.write(json);
		pwr.flush();
		pwr.close();
		
	}	
	
}
