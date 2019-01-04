package com.example.demo.controller;

import com.example.demo.service.PlaySafe;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PlaySafeController {
	@org.springframework.beans.factory.annotation.Autowired
	private PlaySafe playSafe;

	@GetMapping({"/playSafe"})
	public String playSafeController(Model model, HttpServletRequest req) {
		//设定观看视频对应vid，可由req传入
		this.playSafe.setVideoId("7ca55a3c6f84422e3c852a2bf5de56ca_7");
		//设定请求ts
		this.playSafe.setTs(String.valueOf(System.currentTimeMillis()));
		//设定请求者的ip
		this.playSafe.setViewerIp(req.getRemoteAddr());

		//请求token，交付model
		model.addAttribute("token", this.playSafe.getToken());
		model.addAttribute("sign", this.playSafe.getSignForMobile());
		model.addAttribute("ts", this.playSafe.getTs());

		return "playSafe";
	}



}


