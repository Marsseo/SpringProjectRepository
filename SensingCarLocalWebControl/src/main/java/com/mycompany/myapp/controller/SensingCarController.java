package com.mycompany.myapp.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mycompany.myapp.service.SensingCarService;


@Controller
public class SensingCarController {
	
	private static final Logger logger = LoggerFactory.getLogger(SensingCarController.class);
	
	@Autowired
	private SensingCarService service;
	
		
	@RequestMapping("/ultrasonicsensor")
	public void ultrasonicsensor(@RequestParam(defaultValue="90")int angle,
								HttpServletResponse response) throws Exception{
		
		service.changeUltrasonicSensorAngle(angle);
		
		Thread.sleep(500);
		
		int distance = service.getUltrasonicSensorDistance();
				
		JSONObject jsonObject = new JSONObject();
		
		jsonObject.put("distance", distance);
		String json = jsonObject.toString();
		
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter pwr = response.getWriter();
		pwr.write(json);
		pwr.flush();
		pwr.close();
	}
	
	@ExceptionHandler
	public String handleNoAccountException(Exception e, Model model){
		model.addAttribute("reason", e.getMessage());
		
		return "/";
	}
	
	@RequestMapping("/gassensor")
	public void gassensor(HttpServletResponse response ) throws Exception{
		
		double value = service.getGasSensorValue();
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("gas", value);
		
		String json = jsonObject.toString();
		
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter pwr = response.getWriter();
		pwr.write(json);
		pwr.flush();
		pwr.close();
	}
	
}
