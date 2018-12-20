/*     */ package com.example.demo.service;
/*     */ 
/*     */ import com.example.demo.util.HttpClientUtil;
/*     */ import java.io.PrintStream;
/*     */ import java.net.InetAddress;
/*     */ import java.net.UnknownHostException;
/*     */ import java.nio.charset.Charset;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.apache.commons.codec.digest.DigestUtils;
/*     */ import org.apache.http.HttpEntity;
/*     */ import org.apache.http.client.entity.UrlEncodedFormEntity;
/*     */ import org.apache.http.message.BasicNameValuePair;
/*     */ import org.json.JSONObject;
/*     */ import org.springframework.stereotype.Component;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Component
/*     */ public class PlaySafe
/*     */ {
/*  27 */   private String userId = "7ca55a3c6f";
/*  28 */   private String secretKey = "qW4nvoVVi5";
/*  29 */   private String videoId = "7ca55a3c6f84422e3c852a2bf5de56ca_7";
/*  30 */   private String ts = null;
/*  31 */   private String viewerIp = "";
/*  32 */   private String viewerId = "12345";
/*  33 */   private String viewerName = "testUser";
/*  34 */   private String extraParams = "HTML5";
/*  35 */   private String url = "https://hls.videocc.net/service/v1/token";
/*  36 */   private String sign = "";
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void main(String[] args)
/*     */     throws UnknownHostException
/*     */   {
/*  47 */     PlaySafe playSafe = new PlaySafe();
/*  48 */     String address = InetAddress.getLocalHost().getHostAddress().toString();
/*  49 */     playSafe.setViewerIp(address);
/*  50 */     System.out.println("token:" + playSafe.getToken());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getToken()
/*     */   {
/*  63 */     String sign = getSign();
/*     */     
/*  65 */     HttpEntity entity = getData(sign);
/*     */     
/*  67 */     HttpClientUtil client = HttpClientUtil.getInstance();
/*  68 */     String token = client.getToken(this.url, entity);
/*  69 */     JSONObject json = new JSONObject(token);
/*  70 */     json = (JSONObject)json.get("data");
/*  71 */     token = json.getString("token");
/*  72 */     return token;
/*     */   }
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
/*  86 */     if (null == this.ts)
/*  87 */       this.ts = String.valueOf(System.currentTimeMillis());
/*  88 */     String tmp = "extraParams" + this.extraParams + "ts" + this.ts + "userId" + this.userId + "videoId" + this.videoId + "viewerId" + this.viewerId + "viewerIp" + this.viewerIp + "viewerName" + this.viewerName;
/*     */     
/*  90 */     String plain = this.secretKey + tmp + this.secretKey;
/*  91 */     this.sign = DigestUtils.md5Hex(plain.getBytes(Charset.forName("UTF-8")));
/*  92 */     this.sign = this.sign.toUpperCase();
/*  93 */     return this.sign;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getSignForMobile()
/*     */   {
/* 103 */     String plain = this.secretKey + this.videoId + this.ts;
/* 104 */     this.sign = DigestUtils.md5Hex(plain.getBytes(Charset.forName("UTF-8")));
/* 105 */     return this.sign;
/*     */   }
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
/*     */ 
/*     */ 
/*     */ 
/*     */   private HttpEntity getData(String sign)
/*     */   {
/* 134 */     List list = new ArrayList();
/* 135 */     list.add(new BasicNameValuePair("userId", this.userId));
/* 136 */     list.add(new BasicNameValuePair("videoId", this.videoId));
/* 137 */     list.add(new BasicNameValuePair("ts", this.ts));
/* 138 */     list.add(new BasicNameValuePair("viewerIp", this.viewerIp));
/* 139 */     list.add(new BasicNameValuePair("viewerName", this.viewerName));
/* 140 */     list.add(new BasicNameValuePair("extraParams", this.extraParams));
/* 141 */     list.add(new BasicNameValuePair("viewerId", this.viewerId));
/* 142 */     list.add(new BasicNameValuePair("sign", sign));
/* 143 */     HttpEntity entity = null;
/*     */     try {
/* 145 */       entity = new UrlEncodedFormEntity(list);
/*     */     } catch (Exception e) {
/* 147 */       e.printStackTrace();
/*     */     }
/*     */     
/*     */ 
/* 151 */     return entity;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setViewerIp(String ip)
/*     */   {
/* 161 */     this.viewerIp = ip;
/* 162 */     if (null == ip)
/* 163 */       this.viewerIp = "";
/*     */   }
/*     */   
/*     */   public String getTs() {
/* 167 */     return String.valueOf(this.ts);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getUserId()
/*     */   {
/* 176 */     return this.userId;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setUserId(String userId)
/*     */   {
/* 185 */     this.userId = userId;
/* 186 */     if (null == userId) {
/* 187 */       this.userId = "";
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getSecretKey()
/*     */   {
/* 196 */     return this.secretKey;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setSecretKey(String secretKey)
/*     */   {
/* 205 */     this.secretKey = secretKey;
/* 206 */     if (null == secretKey) {
/* 207 */       this.secretKey = "";
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getVideoId()
/*     */   {
/* 216 */     return this.videoId;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setVideoId(String videoId)
/*     */   {
/* 225 */     this.videoId = videoId;
/* 226 */     if (null == videoId) {
/* 227 */       this.videoId = "";
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getViewerId()
/*     */   {
/* 236 */     return this.viewerId;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setViewerId(String viewerId)
/*     */   {
/* 245 */     this.viewerId = viewerId;
/* 246 */     if (null == viewerId) {
/* 247 */       this.viewerId = "";
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getViewerName()
/*     */   {
/* 257 */     return this.viewerName;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setViewerName(String viewerName)
/*     */   {
/* 266 */     this.viewerName = viewerName;
/* 267 */     if (null == viewerName) {
/* 268 */       this.viewerName = "";
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getExtraParams()
/*     */   {
/* 277 */     return this.extraParams;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setExtraParams(String extraParams)
/*     */   {
/* 286 */     this.extraParams = extraParams;
/* 287 */     if (null == extraParams) {
/* 288 */       this.extraParams = "";
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getUrl()
/*     */   {
/* 297 */     return this.url;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setUrl(String url)
/*     */   {
/* 306 */     if (null == url) {
/* 307 */       this.url = "";
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getViewerIp()
/*     */   {
/* 316 */     return this.viewerIp;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setTs(String ts)
/*     */   {
/* 325 */     this.ts = ts;
/*     */   }
/*     */ }


/* Location:              C:\Users\lenovo\Desktop\demo-0.0.1-SNAPSHOT.jar!\BOOT-INF\classes\com\example\demo\service\PlaySafe.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */