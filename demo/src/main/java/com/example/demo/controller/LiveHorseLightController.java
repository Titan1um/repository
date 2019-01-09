package com.example.demo.controller;

import com.example.demo.service.LiveHorseLight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class LiveHorseLightController {
	@Autowired
	LiveHorseLight liveHorseLight;

	@RequestMapping(value = {"/lijunhong/livehorselight"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET}, produces = {"application/json;charset=UTF-8"})
	public String LiveHorseLight(HttpServletRequest req, HttpServletResponse rsp) {
		return liveHorseLight.getJsonString(req);
	}
}
