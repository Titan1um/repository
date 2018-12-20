/*    */ package com.example.demo.controller;
/*    */ 
/*    */ import com.example.demo.service.CallBack;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.web.bind.annotation.GetMapping;
/*    */ import org.springframework.web.bind.annotation.RestController;
/*    */ 
/*    */ 
/*    */ @RestController
/*    */ public class CallbackController
/*    */ {
/*    */   @Autowired
/*    */   private CallBack callBack;
/*    */   
/*    */   @GetMapping({"/Callback"})
/*    */   public String Callback(HttpServletRequest req)
/*    */     throws Exception
/*    */   {
/* 20 */     this.callBack.save(req);
/* 21 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\Users\lenovo\Desktop\demo-0.0.1-SNAPSHOT.jar!\BOOT-INF\classes\com\example\demo\controller\CallbackController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */