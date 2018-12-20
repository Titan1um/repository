/*     */ package com.example.demo.util;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.PrintStream;
/*     */ import java.nio.charset.Charset;
/*     */ import java.security.MessageDigest;
/*     */ import java.security.NoSuchAlgorithmException;
/*     */ import java.util.ArrayList;
/*     */ import org.apache.http.HttpEntity;
/*     */ import org.apache.http.client.ClientProtocolException;
/*     */ import org.apache.http.client.entity.UrlEncodedFormEntity;
/*     */ import org.apache.http.client.methods.CloseableHttpResponse;
/*     */ import org.apache.http.client.methods.HttpPost;
/*     */ import org.apache.http.impl.client.CloseableHttpClient;
/*     */ import org.apache.http.impl.client.HttpClients;
/*     */ import org.apache.http.message.BasicNameValuePair;
/*     */ import org.apache.http.util.EntityUtils;
/*     */ import org.json.JSONArray;
/*     */ import org.json.JSONObject;
/*     */ 
/*     */ 
/*     */ public class GetVideoList
/*     */ {
/*  24 */   private String catatree = "1";
/*  25 */   private String url = "http://api.polyv.net/v2/video/7ca55a3c6f/get-new-list";
/*     */   
/*  27 */   private String userid = "7ca55a3c6f";
/*  28 */   private String secretKey = "qW4nvoVVi5";
/*  29 */   private String ptime = String.valueOf(System.currentTimeMillis());
/*  30 */   private String sign = "";
/*  31 */   private String pageNum = "1";
/*  32 */   private String numPerPage = "10000";
/*  33 */   private String startDate = "2018-11-14";
/*  34 */   private String endDate = "2018-11-27";
/*  35 */   private String format = "json";
/*     */   
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/*     */     try
/*     */     {
/*  42 */       new GetVideoList().getVideoListInPost();
/*  43 */       new GetVideoList().getAllReadyVideo();
/*     */     }
/*     */     catch (Exception e) {
/*  46 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getVideoList()
/*     */   {
/*  55 */     return getVideoListInGet();
/*     */   }
/*     */   
/*     */   public String getVideoListInGet()
/*     */   {
/*  60 */     init();
/*  61 */     HttpClientUtil client = HttpClientUtil.getInstance();
/*  62 */     String res = client.getVideoList(this.url, this.sign);
/*  63 */     return res;
/*     */   }
/*     */   
/*  66 */   public String getVideoListInPost() throws Exception { HttpClientUtil client = HttpClientUtil.getInstance();
/*  67 */     init2();
/*  68 */     ArrayList nvps = new ArrayList();
/*  69 */     nvps.add(new BasicNameValuePair("catatree", this.catatree));
/*  70 */     nvps.add(new BasicNameValuePair("endDate", this.endDate));
/*  71 */     nvps.add(new BasicNameValuePair("format", this.format));
/*  72 */     nvps.add(new BasicNameValuePair("numPerPage", this.numPerPage));
/*  73 */     nvps.add(new BasicNameValuePair("pageNum", this.pageNum));
/*  74 */     nvps.add(new BasicNameValuePair("ptime", this.ptime));
/*  75 */     nvps.add(new BasicNameValuePair("startDate", this.startDate));
/*  76 */     nvps.add(new BasicNameValuePair("sign", this.sign));
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
/*  91 */     String res = client.getToken(this.url, new UrlEncodedFormEntity(nvps, "utf-8"));
/*     */     
/*  93 */     return res;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void init()
/*     */   {
/* 104 */     String reqStr = "catatree=" + this.catatree + "&endDate=" + this.endDate + "&format=" + this.format + "&numPerPage=" + this.numPerPage + "&pageNum=" + this.pageNum + "&ptime=" + this.ptime + "&startDate=" + this.startDate;
/*     */     
/* 106 */     this.url = (this.url + "?" + reqStr);
/*     */     try {
/* 108 */       this.sign = encrypt(reqStr + this.secretKey);
/*     */     }
/*     */     catch (NoSuchAlgorithmException e) {
/* 111 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private void init2()
/*     */   {
/* 120 */     String reqStr = "catatree=" + this.catatree + "&endDate=" + this.endDate + "&format=" + this.format + "&numPerPage=" + this.numPerPage + "&pageNum=" + this.pageNum + "&ptime=" + this.ptime + "&startDate=" + this.startDate + this.secretKey;
/*     */     
/*     */     try
/*     */     {
/* 124 */       this.sign = encrypt(reqStr);
/*     */     } catch (Exception e) {
/* 126 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String encrypt(String decrypt)
/*     */     throws NoSuchAlgorithmException
/*     */   {
/* 138 */     MessageDigest digest = MessageDigest.getInstance("SHA-1");
/* 139 */     digest.update(decrypt.getBytes(Charset.forName("UTF-8")));
/* 140 */     byte[] messageDigest = digest.digest();
/*     */     
/* 142 */     StringBuffer hexString = new StringBuffer();
/* 143 */     for (int i = 0; i < messageDigest.length; i++) {
/* 144 */       String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
/* 145 */       if (shaHex.length() < 2) {
/* 146 */         hexString.append(0);
/*     */       }
/* 148 */       hexString.append(shaHex);
/*     */     }
/* 150 */     return hexString.toString().toUpperCase();
/*     */   }
/*     */   
/*     */   public String getUserid() {
/* 154 */     return this.userid;
/*     */   }
/*     */   
/* 157 */   public void setUserid(String userid) { this.userid = userid; }
/*     */   
/*     */   public String getPtime() {
/* 160 */     return this.ptime;
/*     */   }
/*     */   
/*     */   public void setPtime()
/*     */   {
/* 165 */     long currentTime = System.currentTimeMillis();
/* 166 */     currentTime /= 1000L;
/* 167 */     String cString = String.valueOf(currentTime);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void temp(String url, HttpEntity entity)
/*     */     throws Exception
/*     */   {
/* 175 */     CloseableHttpClient httpClient = HttpClients.createDefault();
/* 176 */     HttpPost httpPost = new HttpPost(url);
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
/* 219 */     httpPost.setEntity(entity);
/* 220 */     System.out.println(entity);
/*     */     try {
/* 222 */       CloseableHttpResponse response = httpClient.execute(httpPost);
/*     */       
/* 224 */       HttpEntity rEntity = response.getEntity();
/* 225 */       String content = EntityUtils.toString(rEntity);
/* 226 */       System.out.println(content);
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
/* 240 */       response.close();
/* 241 */       httpClient.close();
/*     */     }
/*     */     catch (ClientProtocolException e) {
/* 244 */       e.printStackTrace();
/*     */     }
/*     */     catch (IOException e) {
/* 247 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */   public String getAllReadyVideo() {
/* 252 */     String string = getVideoList();
/* 253 */     JSONObject jsonObject = new JSONObject(string);
/* 254 */     JSONArray jsonArray = jsonObject.getJSONArray("data");
/* 255 */     int length = jsonArray.length();
/* 256 */     for (int i = 0; i < length; i++) {
/* 257 */       if (jsonArray.getJSONObject(i).getString("status").equals("61"))
/* 258 */         System.out.println(jsonArray.getJSONObject(i));
/*     */     }
/* 260 */     return jsonObject.toString();
/*     */   }
/*     */ }


/* Location:              C:\Users\lenovo\Desktop\demo-0.0.1-SNAPSHOT.jar!\BOOT-INF\classes\com\example\demo\util\GetVideoList.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */