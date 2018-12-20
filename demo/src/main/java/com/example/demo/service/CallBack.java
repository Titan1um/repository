/*     */ package com.example.demo.service;
/*     */ 
/*     */ import com.example.demo.dao.DBManager;
/*     */ import java.io.PrintStream;
/*     */ import java.nio.charset.Charset;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import org.apache.commons.codec.digest.DigestUtils;
/*     */ import org.json.JSONObject;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Component;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Component
/*     */ public class CallBack
/*     */ {
/*  18 */   private final String _SECRETKEY_ = "qW4nvoVVi5";
/*     */   @Autowired
/*     */   private DBManager dbManager;
/*     */   
/*     */   public void save(HttpServletRequest req) throws Exception {
/*  23 */     String opt = cmpSign(req);
/*  24 */     switch (opt) {
/*     */     case "upload": 
/*  26 */       this.dbManager.saveUpload(saveUpload(req));
/*  27 */       break;
/*     */     case "encode": 
/*  29 */       this.dbManager.saveEncode(saveEncode(req));
/*  30 */       break;
/*     */     case "check": 
/*  32 */       this.dbManager.saveCheck(saveCheck(req));
/*  33 */       break;
/*     */     case "wrong encrypt": 
/*  35 */       System.out.println("Encryption is not right");
/*  36 */       break;
/*     */     default: 
/*  38 */       System.out.println("error:mtfk.");
/*     */     }
/*     */     
/*     */   }
/*     */   
/*     */   private String cmpSign(HttpServletRequest req)
/*     */   {
/*  45 */     String sign = req.getParameter("sign");
/*  46 */     String vid = req.getParameter("vid");
/*  47 */     String plain = "upload" + vid + "qW4nvoVVi5";
/*  48 */     plain = DigestUtils.md5Hex(plain.getBytes(Charset.forName("UTF-8")));
/*  49 */     if (sign.equals(plain)) {
/*  50 */       return "upload";
/*     */     }
/*     */     
/*  53 */     String type = req.getParameter("type");
/*  54 */     plain = "manage" + type + vid + "qW4nvoVVi5";
/*  55 */     plain = DigestUtils.md5Hex(plain.getBytes(Charset.forName("UTF-8")));
/*  56 */     if (sign.equals(plain)) {
/*  57 */       return "check";
/*     */     }
/*     */     
/*  60 */     String df = req.getParameter("df");
/*  61 */     String format = req.getParameter("format");
/*  62 */     plain = "encode" + format + vid + df + "qW4nvoVVi5";
/*  63 */     plain = DigestUtils.md5Hex(plain.getBytes(Charset.forName("UTF-8")));
/*  64 */     if (sign.equals(plain)) {
/*  65 */       return "encode";
/*     */     }
/*     */     
/*     */ 
/*  69 */     System.out.println(vid);
/*  70 */     System.out.println(sign);
/*  71 */     System.out.println("qW4nvoVVi5");
/*  72 */     return "wrong encrypt";
/*     */   }
/*     */   
/*     */   private JSONObject saveUpload(HttpServletRequest req) throws Exception {
/*  76 */     JSONObject jsonObject = new JSONObject();
/*  77 */     jsonObject.put("type", req.getParameter("type"));
/*  78 */     jsonObject.put("vid", req.getParameter("vid"));
/*     */     
/*  80 */     return jsonObject;
/*     */   }
/*     */   
/*     */   private JSONObject saveEncode(HttpServletRequest req) throws Exception {
/*  84 */     JSONObject jsonObject = new JSONObject();
/*  85 */     jsonObject.put("format", req.getParameter("format"));
/*  86 */     jsonObject.put("type", req.getParameter("type"));
/*  87 */     jsonObject.put("vid", req.getParameter("vid"));
/*  88 */     jsonObject.put("df", req.getParameter("df"));
/*     */     
/*  90 */     return jsonObject;
/*     */   }
/*     */   
/*     */   private JSONObject saveCheck(HttpServletRequest req) throws Exception {
/*  94 */     JSONObject jsonObject = new JSONObject();
/*  95 */     jsonObject.put("vid", req.getParameter("vid"));
/*  96 */     jsonObject.put("type", req.getParameter("type"));
/*     */     
/*  98 */     return jsonObject;
/*     */   }
/*     */   
/*     */   public static void main(String[] args) {
/* 102 */     String vid = "7ca55a3c6ff4287723fe8561f452aba2_7";
/* 103 */     String plain = "upload" + vid + "qW4nvoVVi5";
/* 104 */     plain = DigestUtils.md5Hex(plain.getBytes(Charset.forName("UTF-8")));
/* 105 */     System.out.println(plain);
/*     */   }
/*     */ }


/* Location:              C:\Users\lenovo\Desktop\demo-0.0.1-SNAPSHOT.jar!\BOOT-INF\classes\com\example\demo\service\CallBack.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */