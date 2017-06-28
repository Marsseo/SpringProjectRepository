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
	private String ipAddress="192.168.3.50";
	
	@RequestMapping(value = "/hs", method = RequestMethod.GET)
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
		
//		return "home";
		return "charttest2";
	}
	
}
