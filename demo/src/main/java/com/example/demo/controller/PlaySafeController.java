/*    */ package com.example.demo.controller;
/*    */ 
/*    */ import com.example.demo.service.PlaySafe;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import org.springframework.stereotype.Controller;
/*    */ import org.springframework.ui.Model;
/*    */ import org.springframework.web.bind.annotation.GetMapping;
/*    */ 
/*    */ @Controller
/*    */ public class PlaySafeController
/*    */ {
/*    */   @org.springframework.beans.factory.annotation.Autowired
/*    */   private PlaySafe playSafe;
/*    */   
/*    */   @GetMapping({"/playSafe"})
/*    */   public String playSafeController(Model model, HttpServletRequest req)
/*    */   {
/* 18 */     this.playSafe.setViewerIp(req.getRemoteAddr());
/* 19 */     model.addAttribute("token", this.playSafe.getToken());
/* 20 */     model.addAttribute("sign", this.playSafe.getSign());
/* 21 */     model.addAttribute("ts", this.playSafe.getTs());
/* 22 */     return "playSafe";
/*    */   }
/*    */ }


/* Location:              C:\Users\lenovo\Desktop\demo-0.0.1-SNAPSHOT.jar!\BOOT-INF\classes\com\example\demo\controller\PlaySafeController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */