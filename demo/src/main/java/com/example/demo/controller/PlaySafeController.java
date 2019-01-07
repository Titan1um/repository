package com.example.demo.controller;

import com.example.demo.service.PlaySafe;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PlaySafeController {
	@org.springframework.beans.factory.annotation.Autowired
	private PlaySafe playSafe;

	@GetMapping({"/playSafe"})
	public String playSafeController(Model model, HttpServletRequest req) {
		//通过model传入token,sign,ts

		//设定观看视频对应vid，可由req传入
		this.playSafe.setVideoId("7ca55a3c6f84422e3c852a2bf5de56ca_7");
		//设定收到请求时的ts
		this.playSafe.setTs(String.valueOf(System.currentTimeMillis()));
		//设定请求者的ip
		this.playSafe.setViewerIp(req.getRemoteAddr());

		//请求token，交付model
		model.addAttribute("token", this.playSafe.getToken());
		model.addAttribute("sign", this.playSafe.getSignForMobile());
		model.addAttribute("ts", this.playSafe.getTs());

		return "playSafe";
	}




	@GetMapping("/getToken")
	@ResponseBody
	public String playSafeForAll(HttpServletRequest req){
		//通过请求来返回token等到页面

		//设定观看视频对应vid，可由req传入
		this.playSafe.setVideoId(req.getParameter("vid"));
		//设定收到请求时的ts
		this.playSafe.setTs(String.valueOf(System.currentTimeMillis()));
		//设定请求者的ip
		this.playSafe.setViewerIp(req.getRemoteAddr());

		//请求token，包装为json并返回
		JSONObject json = new JSONObject();
		json.put("token",this.playSafe.getToken());
		json.put("sign",this.playSafe.getSignForMobile());
		json.put("ts",this.playSafe.getTs());

		return json.toString();
	}

	@GetMapping("/playSafeAll")
	public String playSafe(HttpServletRequest req,Model model){
		//二者结合的demo

		//设定观看视频对应vid，可由req传入
		this.playSafe.setVideoId("7ca55a3c6f84422e3c852a2bf5de56ca_7");
		//设定收到请求时的ts
		this.playSafe.setTs(String.valueOf(System.currentTimeMillis()));
		//设置请求者IP
		this.playSafe.setViewerIp(req.getRemoteAddr());

		//请求token，交付model
		model.addAttribute("token",this.playSafe.getToken());
		model.addAttribute("sign",this.playSafe.getSignForMobile());
		model.addAttribute("ts",this.playSafe.getTs());

		return "playSafeAll";
	}
}


