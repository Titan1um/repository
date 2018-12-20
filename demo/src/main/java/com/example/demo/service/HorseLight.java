/*     */ package com.example.demo.service;
/*     */ 
/*     */ import com.example.demo.util.JSonObject;
/*     */ import java.io.PrintStream;
/*     */ import java.nio.charset.Charset;
/*     */ import org.apache.commons.codec.digest.DigestUtils;
/*     */ import org.springframework.stereotype.Component;
/*     */ 
/*     */ 
/*     */ 
/*     */ @Component
/*     */ public class HorseLight
/*     */ {
/*  14 */   private String vid = null;
/*  15 */   private String secretKey = "qW4nvoVVi5";
/*  16 */   private String username = "jun";
/*  17 */   private String code = null;
/*  18 */   private int status = 1;
/*  19 */   private String t = null;
/*     */   
/*     */ 
/*  22 */   private String sign = null;
/*  23 */   private String msg = "Errormessage!";
/*     */   
/*  25 */   private String fontSize = "40";
/*  26 */   private String fontColor = "0xFFE900";
/*  27 */   private String speed = "200";
/*  28 */   private String filter = "on";
/*  29 */   private String setting = "3";
/*  30 */   private String alpha = "1";
/*  31 */   private String filterAlpha = "1";
/*  32 */   private String filterColor = "0x3914AF";
/*  33 */   private String blurX = "2";
/*  34 */   private String blurY = "2";
/*  35 */   private String tweenTime = "1";
/*  36 */   private String interval = "5";
/*  37 */   private String lifeTime = "3";
/*  38 */   private String strength = "4";
/*  39 */   private String show = "on";
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getSign()
/*     */   {
/*  64 */     String plain = "vid=" + this.vid + "&secretkey=" + this.secretKey + "&username=" + this.username + "&code=" + this.code + "&status=" + this.status + "&t=" + this.t;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  72 */     System.out.println(plain);
/*  73 */     this.sign = DigestUtils.md5Hex(plain.getBytes(Charset.forName("UTF-8")));
/*  74 */     return this.sign;
/*     */   }
/*     */   
/*     */   public JSonObject getJson() {
/*  78 */     getSign();
/*  79 */     JSonObject object = new JSonObject();
/*  80 */     object.put("status", 1);
/*  81 */     object.put("username", this.username);
/*  82 */     object.put("sign", this.sign);
/*  83 */     object.put("msg", this.msg);
/*  84 */     object.put("fontSize", this.fontSize);
/*  85 */     object.put("fontColor", this.fontColor);
/*  86 */     object.put("speed", this.speed);
/*  87 */     object.put("filter", this.filter);
/*  88 */     object.put("setting", this.setting);
/*  89 */     object.put("alpha", this.alpha);
/*  90 */     object.put("filterAlpha", this.filterAlpha);
/*  91 */     object.put("filterColor", this.filterColor);
/*  92 */     object.put("blurX", this.blurX);
/*  93 */     object.put("blurY", this.blurY);
/*  94 */     object.put("tweenTime", this.tweenTime);
/*  95 */     object.put("interval", this.interval);
/*  96 */     object.put("lifeTime", this.lifeTime);
/*  97 */     object.put("strength", this.strength);
/*  98 */     object.put("show", this.show);
/*  99 */     return object;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/* 107 */     HorseLight horseLight = new HorseLight();
/* 108 */     horseLight.setT("143020010115550947");
/* 109 */     horseLight.setCode("abc");
/* 110 */     horseLight.setVid("8f8482aaab11dd5f45f183a9192a04c5_8");
/* 111 */     horseLight.setUsername("suki");
/* 112 */     horseLight.setSecretKey("AiDQw1mAmi");
/* 113 */     System.out.println("=========s=i=g=n==" + horseLight.getSign() + "=============");
/*     */   }
/*     */   
/*     */   public void setVid(String vid)
/*     */   {
/* 118 */     this.vid = vid;
/* 119 */     if (null == vid)
/* 120 */       this.vid = "";
/*     */   }
/*     */   
/*     */   public void setCode(String code) {
/* 124 */     this.code = code;
/* 125 */     if (null == code)
/* 126 */       this.code = "";
/*     */   }
/*     */   
/*     */   public void setT(String t) {
/* 130 */     this.t = t;
/* 131 */     if (null == t)
/* 132 */       this.t = "";
/*     */   }
/*     */   
/*     */   public void setUsername(String username) {
/* 136 */     this.username = username;
/* 137 */     if (null == username) {
/* 138 */       this.username = "";
/*     */     }
/*     */   }
/*     */   
/*     */   public void setSecretKey(String secretKey) {
/* 143 */     this.secretKey = secretKey;
/*     */   }
/*     */ }


/* Location:              C:\Users\lenovo\Desktop\demo-0.0.1-SNAPSHOT.jar!\BOOT-INF\classes\com\example\demo\service\HorseLight.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */