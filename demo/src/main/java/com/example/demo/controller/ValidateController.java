/*    */ package com.example.demo.controller;
/*    */ 
/*    */ import com.example.demo.service.HorseLight;
/*    */ import com.example.demo.util.JSonObject;
/*    */ import java.io.PrintStream;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import javax.servlet.http.HttpServletResponse;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.web.bind.annotation.RequestMapping;
/*    */ import org.springframework.web.bind.annotation.RestController;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @RestController
/*    */ public class ValidateController
/*    */ {
/*    */   @Autowired
/*    */   HorseLight horseLight;
/*    */   
/*    */   @RequestMapping(value={"/lijunhong/horseLight.php"}, method={org.springframework.web.bind.annotation.RequestMethod.GET}, produces={"application/json;charset=UTF-8"})
/*    */   public String horseLight(HttpServletRequest req, HttpServletResponse rsp)
/*    */   {
/* 24 */     rsp.addHeader("Access-Control-Allow-Origin", "*");
/* 25 */     rsp.setHeader("Content-Type", "application/json;charset=UTF-8");
/* 26 */     rsp.addHeader("Connection", "keep-alive");
/* 27 */     rsp.addHeader("Server", "Tengine/2.1.0");
/* 28 */     rsp.addHeader("Vary", "Accept-Encoding");
/* 29 */     rsp.addHeader("Transfer-Encoding", "chunked");
/*    */     
/*    */ 
/*    */ 
/* 33 */     this.horseLight.setVid(req.getParameter("vid"));
/* 34 */     this.horseLight.setCode(req.getParameter("code"));
/* 35 */     this.horseLight.setT(req.getParameter("t"));
/* 36 */     String prefix = req.getParameter("callback");
/* 37 */     this.horseLight.setUsername("Ti");
/* 38 */     JSonObject jsonpObject = this.horseLight.getJson();
/*    */     
/*    */ 
/* 41 */     String reString = null;
/* 42 */     if (null == prefix) {
/* 43 */       reString = jsonpObject.toString();
/*    */     } else
/* 45 */       reString = prefix + "(" + jsonpObject.toString() + ")";
/* 46 */     System.out.println(reString);
/*    */     
/*    */ 
/*    */ 
/*    */ 
/* 51 */     return reString;
/*    */   }
/*    */ }


/* Location:              C:\Users\lenovo\Desktop\demo-0.0.1-SNAPSHOT.jar!\BOOT-INF\classes\com\example\demo\controller\ValidateController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */