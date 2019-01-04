package com.example.demo.controller;

import com.example.demo.service.HorseLight;
import com.example.demo.util.JSonObject;

import java.io.PrintStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ValidateController {
	@Autowired
	HorseLight horseLight;

	@RequestMapping(value = {"/lijunhong/horseLight.php"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET}, produces = {"application/json;charset=UTF-8"})
	public String horseLight(HttpServletRequest req, HttpServletResponse rsp) {
		rsp.addHeader("Access-Control-Allow-Origin", "*");
		rsp.setHeader("Content-Type", "application/json;charset=UTF-8");
		rsp.addHeader("Connection", "keep-alive");
		rsp.addHeader("Server", "Tengine/2.1.0");
		rsp.addHeader("Vary", "Accept-Encoding");
		rsp.addHeader("Transfer-Encoding", "chunked");


		this.horseLight.setVid(req.getParameter("vid"));
		this.horseLight.setCode(req.getParameter("code"));
		this.horseLight.setT(req.getParameter("t"));
		String prefix = req.getParameter("callback");
		this.horseLight.setUsername("Ti");
		JSonObject jsonpObject = this.horseLight.getJson();


		String reString = null;
		if (null == prefix) {
			reString = jsonpObject.toString();
		} else {
			reString = prefix + "(" + jsonpObject.toString() + ")";
		}
		System.out.println(reString);


		return reString;
	}
}
