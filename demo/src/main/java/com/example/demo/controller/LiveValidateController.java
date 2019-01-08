package com.example.demo.controller;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.LiveValidator;

/**
* @Description: 直播自定义授权与外部授权demo
 *用@RestController与@ResponseBody均可
* @Author: LJH
*/
@RestController
@CrossOrigin
public class LiveValidateController {

	/**
	* @Description: 自定义授权
	* @Param: [req]
	* @return: java.lang.String
	* @Author: LJH
	*/
	@GetMapping(value = "/selfDefine")
	@ResponseBody
	public String selfDefine(HttpServletRequest req) {
		System.out.println("selfDefine");
		return new LiveValidator().getSDCallBack(req);
	}

	/**
	* @Description: 外部授权
	* @Param: [req]
	* @return: java.lang.String
	* @Author: LJH
	*/
	@GetMapping(value = "/outerValidate",produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String outerValidate(HttpServletRequest req) {
		System.out.println("outerValidate");
		return new LiveValidator().getOVCallBack(req);
	}

	/**
	* @Description: 客户接入案例
	* @Param: [req, resp]
	* @return: java.lang.String
	* @Author: LJH
	*/
	@GetMapping(value = "/ov",produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String outerValidateTest(ServletRequest req,ServletResponse resp) throws Exception {
		System.out.println("ov");
		return new LiveValidator().outerValidate(req, resp);
	}

}
