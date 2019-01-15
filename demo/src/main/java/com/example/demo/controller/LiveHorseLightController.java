package com.example.demo.controller;

import com.example.demo.service.LiveHorseLight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin
public class LiveHorseLightController {
	@Autowired
	LiveHorseLight liveHorseLight;

	/**
	* @Description:  直播跑马灯
	* @Param: [req, rsp]
	* @return: java.lang.String
	*/
	@GetMapping("/lijunhong/livehorselight")
	public String LiveHorseLight(HttpServletRequest req, HttpServletResponse rsp) {
		return liveHorseLight.getJsonString(req);
	}

	@GetMapping("/lijunhong/livevalidate",produces = "application/json;charset=UTF-8")
	public String LiveValidate(HttpServletRequest req, HttpServletResponse rsp){
		return liveHorseLight.getJsonForValidateOnly(req);
	}
}
