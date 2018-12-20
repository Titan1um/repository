/*    */ package com.example.demo.controller;
/*    */ 
/*    */ import com.example.demo.service.PlaySafe;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Controller;
/*    */ import org.springframework.ui.Model;
/*    */ import org.springframework.web.bind.annotation.GetMapping;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Controller
/*    */ public class MobilePlaySafe
/*    */ {
/*    */   @Autowired
/*    */   private PlaySafe playSafe;
/*    */   
/*    */   @GetMapping({"/mobile"})
/*    */   public String mobile(Model model)
/*    */   {
/* 21 */     String ts = String.valueOf(System.currentTimeMillis());
/* 22 */     this.playSafe.setTs(ts);
/* 23 */     model.addAttribute("ts", ts);
/* 24 */     model.addAttribute("sign", this.playSafe.getSignForMobile());
/*    */     
/* 26 */     return "mobile";
/*    */   }
/*    */ }


/* Location:              C:\Users\lenovo\Desktop\demo-0.0.1-SNAPSHOT.jar!\BOOT-INF\classes\com\example\demo\controller\MobilePlaySafe.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */