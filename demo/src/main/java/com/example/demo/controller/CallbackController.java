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

	/**
	* @Description: CallBackController
	* @Param: [req]
	* @return: java.lang.String
	* @Author: LJH
	*/
	@GetMapping({"/Callback"})
	public String callBack(HttpServletRequest req) {
		this.callBack.save(req);
		return null;
	}

	/**
	* @Description:  用此url测试ConnectUtil能否连接db成功
	* @Param: [req]
	* @return: java.lang.String
	* @Author: LJH
	*/
	@GetMapping({"/CallbackTest"})
	public String CallBackTest(HttpServletRequest req) {
		try {
			util.getInstance();
		} catch (Exception e) {
			infoLogger.log(e.toString());
			e.printStackTrace();
			return "error occurs";
		}
		return "ojbk";
	}

}


