package com.example.demo.controller;

import org.springframework.stereotype.Controller;

@Controller
public class controller
{
  @org.springframework.web.bind.annotation.GetMapping({"/demo"})
  public String demo() {
    return "demo";
  }
}
