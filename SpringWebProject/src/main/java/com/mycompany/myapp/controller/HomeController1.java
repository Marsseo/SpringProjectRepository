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
public class HomeController1 {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController1.class);
	private String ipAddress=IpAdress.getIpAddress();
	
	@RequestMapping(value = "/cm", method = RequestMethod.GET)
	public String home(Model model) {
		
		CoapClient coapClient = new CoapClient();
		JSONObject jsonObject = null;
		String json = null;
		CoapResponse coapResponse = null;
		
		
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
		
		return "charttest1";
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
	
}
