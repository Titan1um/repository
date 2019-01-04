package com.example.demo.controller;

import com.example.demo.service.PlaySafe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MobilePlaySafe {
	@Autowired
	private PlaySafe playSafe;

	@GetMapping({"/mobile"})
	public String mobile(Model model) {
		String ts = String.valueOf(System.currentTimeMillis());
		this.playSafe.setTs(ts);
		this.playSafe.setVideoId("7ca55a3c6fde1f2833c9d6d235395020_7");
		model.addAttribute("ts", this.playSafe.getTs());
		model.addAttribute("sign", this.playSafe.getSignForMobile());
		return "mobile";
	}
}
