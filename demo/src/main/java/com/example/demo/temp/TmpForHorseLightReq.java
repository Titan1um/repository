/*    */ package com.example.demo.temp;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.OutputStream;
/*    */ import javax.servlet.http.HttpServletResponse;
/*    */ import org.apache.http.entity.StringEntity;
/*    */ import org.apache.http.util.EntityUtils;
/*    */ 
/*    */ public class TmpForHorseLightReq
/*    */ {
/*    */   public static void tmp(HttpServletResponse resp, String json) throws IOException
/*    */   {
/* 13 */     OutputStream os = resp.getOutputStream();
/* 14 */     StringEntity entity = new StringEntity(json);
/* 15 */     os.write(EntityUtils.toByteArray(entity));
/*    */   }
/*    */ }


/* Location:              C:\Users\lenovo\Desktop\demo-0.0.1-SNAPSHOT.jar!\BOOT-INF\classes\com\example\demo\temp\TmpForHorseLightReq.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */