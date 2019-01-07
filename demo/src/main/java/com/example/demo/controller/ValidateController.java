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
	* @Description:  跑马灯授权返回示例
	 *                此处的requestMapping URL是为了统一php服务器与java服务器
	 *                若不想用RestController可用@ResponseBody
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

		//接受req参数并设置,亦可在HorseLight中新建接收req再初始化减少controller操作和可读
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
}
