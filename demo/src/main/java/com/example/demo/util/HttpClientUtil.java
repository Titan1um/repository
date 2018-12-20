/*     */ package com.example.demo.util;
/*     */ 
/*     */ import java.io.PrintStream;
/*     */ import java.util.List;
/*     */ import org.apache.http.HttpEntity;
/*     */ import org.apache.http.StatusLine;
/*     */ import org.apache.http.client.entity.UrlEncodedFormEntity;
/*     */ import org.apache.http.client.methods.CloseableHttpResponse;
/*     */ import org.apache.http.client.methods.HttpGet;
/*     */ import org.apache.http.client.methods.HttpPost;
/*     */ import org.apache.http.entity.StringEntity;
/*     */ import org.apache.http.impl.client.CloseableHttpClient;
/*     */ import org.apache.http.impl.client.HttpClients;
/*     */ import org.apache.http.util.EntityUtils;
/*     */ import org.json.JSONObject;
/*     */ 
/*     */ public class HttpClientUtil
/*     */ {
/*  19 */   private static HttpClientUtil httpClient = null;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static HttpClientUtil getInstance()
/*     */   {
/*  28 */     if (null == httpClient) {
/*  29 */       httpClient = new HttpClientUtil();
/*  30 */       return httpClient;
/*     */     }
/*  32 */     return httpClient;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private HttpPost setPost(String url, HttpEntity entity)
/*     */   {
/*  43 */     HttpPost post = new HttpPost(url);
/*  44 */     post.setEntity(entity);
/*  45 */     return post;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private HttpPost setPost(String url, JSONObject jsonObject)
/*     */     throws Exception
/*     */   {
/*  55 */     HttpPost post = new HttpPost(url);
/*  56 */     StringEntity entity = new StringEntity(jsonObject.toString());
/*  57 */     post.setEntity(entity);
/*  58 */     return post;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private String doPost(HttpPost httpPost)
/*     */   {
/*  69 */     CloseableHttpClient httpClient = null;
/*  70 */     CloseableHttpResponse response = null;
/*  71 */     String token = null;
/*     */     
/*     */     try
/*     */     {
/*  75 */       httpClient = HttpClients.createDefault();
/*     */       
/*     */ 
/*  78 */       response = httpClient.execute(httpPost);
/*  79 */       System.out.println("Http请求状态码：{}" + response.getStatusLine().getStatusCode());
/*     */       
/*  81 */       System.out.println("httppost:" + EntityUtils.toString(httpPost.getEntity()) + "\n==============================================");
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
/*  94 */       token = getReponseContent(response);
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */       try
/*     */       {
/* 101 */         response.close();
/* 102 */         httpClient.close();
/*     */       }
/*     */       catch (Exception e) {
/* 105 */         e.printStackTrace();
/*     */       }
/*     */       
/* 108 */       System.out.println("=================>" + token + "<=================");
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/*  98 */       e.printStackTrace();
/*     */     } finally {
/*     */       try {
/* 101 */         response.close();
/* 102 */         httpClient.close();
/*     */       }
/*     */       catch (Exception e) {
/* 105 */         e.printStackTrace();
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 110 */     return token;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public HttpGet setGet(String url)
/*     */   {
/* 121 */     HttpGet httpGet = new HttpGet(url);
/* 122 */     return httpGet;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String doGet(HttpGet httpGet)
/*     */   {
/* 131 */     CloseableHttpClient client = null;
/* 132 */     CloseableHttpResponse response = null;
/* 133 */     reString = null;
/*     */     try {
/* 135 */       client = HttpClients.createDefault();
/* 136 */       response = client.execute(httpGet);
/* 137 */       return EntityUtils.toString(response.getEntity());
/*     */     } catch (Exception e) {
/* 139 */       e.printStackTrace();
/*     */     } finally {
/*     */       try {
/* 142 */         response.close();
/* 143 */         client.close();
/*     */       }
/*     */       catch (Exception e) {
/* 146 */         e.printStackTrace();
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getVideoList(String url, String sign)
/*     */   {
/* 160 */     String req = url + "&sign=" + sign;
/* 161 */     HttpGet httpGet = new HttpGet(req);
/* 162 */     return doGet(httpGet);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getToken(String url, HttpEntity entity)
/*     */   {
/* 174 */     return doPost(setPost(url, entity));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getToken(String url, JSONObject json)
/*     */   {
/* 185 */     StringEntity entity = new StringEntity(json.toString(), "utf-8");
/* 186 */     return doPost(setPost(url, entity));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getToken(String url, List list)
/*     */     throws java.io.UnsupportedEncodingException
/*     */   {
/* 196 */     HttpEntity entity = null;
/* 197 */     entity = new UrlEncodedFormEntity(list, org.apache.commons.codec.Charsets.UTF_8);
/* 198 */     return doPost(setPost(url, entity));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private String getReponseContent(CloseableHttpResponse response)
/*     */     throws Exception
/*     */   {
/* 211 */     String readContent = null;
/* 212 */     HttpEntity entity = response.getEntity();
/* 213 */     readContent = EntityUtils.toString(entity);
/* 214 */     System.out.println(readContent);
/* 215 */     return readContent;
/*     */   }
/*     */ }


/* Location:              C:\Users\lenovo\Desktop\demo-0.0.1-SNAPSHOT.jar!\BOOT-INF\classes\com\example\demo\util\HttpClientUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */