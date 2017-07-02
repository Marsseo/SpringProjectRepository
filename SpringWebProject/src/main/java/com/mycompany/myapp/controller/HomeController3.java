//package com.mycompany.myapp.controller;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.util.Locale;
//
//import javax.servlet.http.HttpServletResponse;
//
//import org.eclipse.californium.core.CoapClient;
//import org.eclipse.californium.core.CoapResponse;
//import org.eclipse.californium.core.coap.MediaTypeRegistry;
//import org.json.JSONObject;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//
//@Controller
//public class HomeController3 {
//	
//	private static final Logger logger = LoggerFactory.getLogger(HomeController3.class);
//	private String ipAddress = IpAdress.getIpAddress();
//	
//	@RequestMapping(value = "/dj", method = RequestMethod.GET)
//	public String home(Model model) {
//		
//		CoapClient coapClient = new CoapClient();
//		JSONObject jsonObject = null;
//		String json = null;
//		CoapResponse coapResponse = null;
//		
//		// --------------------------------------------------
//		jsonObject = new JSONObject();
//		logger.info("");
//		jsonObject.put("command", "status");
//		json = jsonObject.toString();
//		coapClient.setURI("coap://"+ipAddress+"/camera");
//		coapResponse = coapClient.post(json, MediaTypeRegistry.APPLICATION_JSON);
//		json = coapResponse.getResponseText();
//		jsonObject = new JSONObject(json);
//		model.addAttribute("leftright", jsonObject.getString("leftright"));
//		model.addAttribute("updown", jsonObject.getString("updown"));
//		
//		// -------------------------------------------------------
//		jsonObject = new JSONObject();
//		jsonObject.put("command", "status");
//		json = jsonObject.toString();
//		coapClient.setURI("coap://"+ipAddress+"/rgbled");
//		coapResponse = coapClient.post(json, MediaTypeRegistry.APPLICATION_JSON);
//		json = coapResponse.getResponseText();
//		jsonObject = new JSONObject(json);
//		model.addAttribute("red", jsonObject.getString("red"));
//		model.addAttribute("green", jsonObject.getString("green"));
//		model.addAttribute("blue", jsonObject.getString("blue"));
//		
//		model.addAttribute("cameraUrl", "http://"+ipAddress+":50001?action=stream");
//		return "charttest3";
//	}
//	
//	@RequestMapping("/camera")
//	public void camera(String command, String leftright, String updown, HttpServletResponse response)
//			throws IOException {
//		JSONObject jsonObject = new JSONObject();
//		jsonObject.put("command", command);
//		jsonObject.put("leftright", leftright);
//		jsonObject.put("updown", updown);
//		String reqJson = jsonObject.toString();
//		
//		CoapClient coapClient = new CoapClient();
//		coapClient.setURI("coap://"+ipAddress+"/camera");
//		CoapResponse coapResponse = coapClient.post(reqJson, MediaTypeRegistry.APPLICATION_JSON);
//		String resJson = coapResponse.getResponseText();
//		coapClient.shutdown();
//		
//		response.setContentType("application/json; charset=UTF-8");
//		PrintWriter pw = response.getWriter();
//		pw.write(resJson);
//		pw.flush();
//		pw.close();
//	}
//	
//	@RequestMapping("/rgbled")
//	public void rgbled(String command, String red, String green, String blue,
//			HttpServletResponse response)
//			throws IOException {
//		JSONObject jsonObject = new JSONObject();
//		jsonObject.put("command", command);
//		jsonObject.put("red", red);
//		jsonObject.put("green", green);
//		jsonObject.put("blue", blue);
//		String reqJson = jsonObject.toString();
//		
//		CoapClient coapClient = new CoapClient();
//		coapClient.setURI("coap://"+ipAddress+"/rgbled");
//		CoapResponse coapResponse = coapClient.post(reqJson,
//				MediaTypeRegistry.APPLICATION_JSON);
//		String resJson = coapResponse.getResponseText();
//		coapClient.shutdown();
//		
//		response.setContentType("application/json; charset=UTF-8");
//		PrintWriter pw = response.getWriter();
//		pw.write(resJson);
//		pw.flush();
//		pw.close();
//	}
//	
//}
