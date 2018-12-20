/*    */ package com.example.demo.controller;
/*    */ 
/*    */ import org.springframework.stereotype.Controller;
/*    */ 
/*    */ @Controller
/*    */ public class controller
/*    */ {
/*    */   @org.springframework.web.bind.annotation.GetMapping({"/demo"})
/*    */   public String demo() {
/* 10 */     return "demo";
/*    */   }
/*    */ }


/* Location:              C:\Users\lenovo\Desktop\demo-0.0.1-SNAPSHOT.jar!\BOOT-INF\classes\com\example\demo\controller\controller.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */