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

	@@GetMapping("/lijunhong/livehorselight")
	public String LiveHorseLight(HttpServletRequest req, HttpServletResponse rsp) {
		return liveHorseLight.getJsonString(req);
	}
}
