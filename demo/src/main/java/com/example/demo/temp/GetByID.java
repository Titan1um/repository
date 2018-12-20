/*    */ package com.example.demo.temp;
/*    */ 
/*    */ import org.apache.http.client.methods.CloseableHttpResponse;
/*    */ import org.apache.http.client.methods.HttpGet;
/*    */ import org.apache.http.impl.client.CloseableHttpClient;
/*    */ import org.apache.http.impl.client.HttpClients;
/*    */ import org.apache.http.util.EntityUtils;
/*    */ 
/*    */ public class GetByID
/*    */ {
/*    */   public static void main(String[] args)
/*    */   {
/* 13 */     String url = "http://v.polyv.net/uc/services/rest?method=getById";
/* 14 */     String readtoken = "66d670ea-c227-42dd-ac99-dd7dad85d23f";
/* 15 */     String vid = "7ca55a3c6fb1f445d9ab845be127b10b_7";
/* 16 */     String req = url + "&vid=" + vid + "&readtoken=" + readtoken;
/*    */     
/* 18 */     CloseableHttpClient client = HttpClients.createDefault();
/* 19 */     HttpGet httpGet = new HttpGet(req);
/*    */     try {
/* 21 */       CloseableHttpResponse response = client.execute(httpGet);
/* 22 */       System.out.println(EntityUtils.toString(response.getEntity()));
/*    */     } catch (Exception e) {
/* 24 */       e.printStackTrace();
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\lenovo\Desktop\demo-0.0.1-SNAPSHOT.jar!\BOOT-INF\classes\com\example\demo\temp\GetByID.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */