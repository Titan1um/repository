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

	/**
	 * @Description: 跑马灯授权返回示例
	 * 此处的requestMapping URL是为了统一php服务与其他服务，用户应自定
	 * 若不想用RestController可用@ResponseBody
	 * @Param: [req, rsp]
	 * @return: java.lang.String
	 * @Author: LJH
	 */
	@RequestMapping(value = {"/lijunhong/horseLight.php"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET}, produces = {"application/json;charset=UTF-8"})
	public String horseLight(HttpServletRequest req, HttpServletResponse rsp) {
//      无需设置,若不通可尝试
//		rsp.addHeader("Access-Control-Allow-Origin", "*");
//		rsp.setHeader("Content-Type", "application/json;charset=UTF-8");
//		rsp.addHeader("Connection", "keep-alive");
//		rsp.addHeader("Server", "Tengine/2.1.0");
//		rsp.addHeader("Vary", "Accept-Encoding");
//		rsp.addHeader("Transfer-Encoding", "chunked");

		//接受req参数并设置
		this.horseLight.setVid(req.getParameter("vid"));
		this.horseLight.setCode(req.getParameter("code"));
		this.horseLight.setT(req.getParameter("t"));
		String prefix = req.getParameter("callback");
		this.horseLight.setUsername("Ti");
		JSonObject jsonpObject = this.horseLight.getJson();


		//若有则处理jsonp
		String reString = null;
		if (null == prefix) {
			reString = jsonpObject.toString();
		} else {
			reString = prefix + "(" + jsonpObject.toString() + ")";
		}
		System.out.println(reString);


		return reString;
	}

	/**
	 * @Description: 播放授权验证返回示例
	 * 此处的requestMapping URL是为了统一php服务与其他服务，用户应自定
	 * 若不想用RestController可用@ResponseBody
	 * @Param: [req, rsp]
	 * @return: java.lang.String
	 * @Author: LJH
	 */
	@RequestMapping(value = {"/lijunhong/validate.php"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET}, produces = {"application/json;charset=UTF-8"})
	public String Validate(HttpServletRequest req, HttpServletResponse rsp) {
//      无需设置,若不通可尝试
//		rsp.addHeader("Access-Control-Allow-Origin", "*");
//		rsp.setHeader("Content-Type", "application/json;charset=UTF-8");
//		rsp.addHeader("Connection", "keep-alive");
//		rsp.addHeader("Server", "Tengine/2.1.0");
//		rsp.addHeader("Vary", "Accept-Encoding");
//		rsp.addHeader("Transfer-Encoding", "chunked");

		//接受req参数并设置
		this.horseLight.setVid(req.getParameter("vid"));
		this.horseLight.setCode(req.getParameter("code"));
		this.horseLight.setT(req.getParameter("t"));
		//此时username为"",或自定义
		this.horseLight.setUsername("");
		String prefix = req.getParameter("callback");
		JSonObject jsonpObject = this.horseLight.getJsonForValidateOnly();


		//若有则处理jsonp
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
