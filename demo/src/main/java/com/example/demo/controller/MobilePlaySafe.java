package com.example.demo.controller;

import com.example.demo.service.PlaySafe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;




@Controller
public class MobilePlaySafe
{
  @Autowired
  private PlaySafe playSafe;

  @GetMapping({"/mobile"})
  public String mobile(Model model)
  {
    String ts = String.valueOf(System.currentTimeMillis());
    this.playSafe.setTs(ts);
    model.addAttribute("ts", ts);
    model.addAttribute("sign", this.playSafe.getSignForMobile());

    return "mobile";
  }
}
