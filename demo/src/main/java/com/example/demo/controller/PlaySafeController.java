package com.example.demo.controller;

import com.example.demo.service.PlaySafe;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PlaySafeController
{
  @org.springframework.beans.factory.annotation.Autowired
  private PlaySafe playSafe;

  @GetMapping({"/playSafe"})
  public String playSafeController(Model model, HttpServletRequest req)
  {
  	this.playSafe.setVideoId("7ca55a3c6f84422e3c852a2bf5de56ca_7");
    this.playSafe.setViewerIp(req.getRemoteAddr());
    model.addAttribute("token", this.playSafe.getToken());
    model.addAttribute("sign", this.playSafe.getSign());
    model.addAttribute("ts", this.playSafe.getTs());
    return "playSafe";
  }

	@GetMapping({"/music"})
	public String musicController(Model model, HttpServletRequest req)
	{
		this.playSafe.setVideoId("7ca55a3c6f33c7950efa7d33dadf30e5_7");
		this.playSafe.setViewerIp(req.getRemoteAddr());
		model.addAttribute("token", this.playSafe.getToken());
		model.addAttribute("sign", this.playSafe.getSign());
		model.addAttribute("ts", this.playSafe.getTs());
		return "music";
	}

}


