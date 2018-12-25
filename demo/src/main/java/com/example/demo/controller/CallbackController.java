package com.example.demo.controller;

import com.example.demo.service.CallBack;

import javax.servlet.http.HttpServletRequest;

import com.example.demo.util.ConnectUtil;
import com.example.demo.util.InfoLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class CallbackController {
	@Autowired
	private CallBack callBack;
	@Autowired
	ConnectUtil util;
	@Autowired
	InfoLogger infoLogger;

	@GetMapping({"/Callback"})
	public String callBack(HttpServletRequest req) {
		this.callBack.save(req);
		return null;
	}

	@GetMapping({"/CallbackTest"})
	public String CallBackTest(HttpServletRequest req) {
		try {
			util.getInstance();
		} catch (Exception e) {
			infoLogger.log(e.toString());
			e.printStackTrace();
		}
		return null;
	}

}


