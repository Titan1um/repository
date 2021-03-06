package com.jun.kxpwd.controller;

import com.jun.kxpwd.dao.DBManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

/**
 * @description:
 * @create: 2019-04-23 00:56
 * @author: jun
 */
@RestController
public class home {

	@Autowired
	DBManager dbManager;


	@GetMapping("/pwd")
	public String pwd(HttpServletRequest request){
		System.out.println("id:"+request.getParameter("id"));
		System.out.println("pwd:"+request.getParameter("pwd"));
		try {
			dbManager.save(request.getParameter("id"),request.getParameter("pwd"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "";
	}
}
