package com.jun.kxpwd.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @description:
 * @create: 2019-04-23 00:56
 * @author: jun
 */
@RestController
public class home {

	@GetMapping("/pwd")
	public String pwd(HttpServletRequest request){
		System.out.println(request.getParameter("id"));
		System.out.println(request.getParameter("pwd"));
		return "";
	}
}
