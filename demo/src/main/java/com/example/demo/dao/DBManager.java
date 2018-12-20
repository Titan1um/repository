/*    */ package com.example.demo.dao;
/*    */ 
/*    */ import com.example.demo.util.ConnectUtil;
/*    */ import java.io.PrintStream;
/*    */ import java.sql.Connection;
/*    */ import java.sql.PreparedStatement;
/*    */ import org.json.JSONObject;
/*    */ 
/*    */ @org.springframework.stereotype.Component
/*    */ public class DBManager
/*    */ {
/* 12 */   Connection conn = null;
/*    */   
/*    */   public void saveUpload(JSONObject jsonObject) throws Exception {
/* 15 */     String sql = "insert into upload(type,vid,t) values(?,?,current_date())";
/* 16 */     this.conn = ConnectUtil.getInstance();
/* 17 */     PreparedStatement pst = this.conn.prepareStatement(sql);
/* 18 */     pst.setString(1, jsonObject.getString("type"));
/* 19 */     pst.setString(2, jsonObject.getString("vid"));
/* 20 */     pst.executeUpdate();
/* 21 */     System.out.println("Saved upload.");
/*    */   }
/*    */   
/* 24 */   public void saveEncode(JSONObject jsonObject) throws Exception { String sql = "insert into encode(type,vid,format,df,t) values(?,?,?,?,current_date())";
/* 25 */     this.conn = ConnectUtil.getInstance();
/* 26 */     PreparedStatement pst = this.conn.prepareStatement(sql);
/* 27 */     pst.setString(1, jsonObject.getString("type"));
/* 28 */     pst.setString(2, jsonObject.getString("vid"));
/* 29 */     pst.setString(3, jsonObject.getString("format"));
/* 30 */     pst.setString(4, jsonObject.getString("df"));
/* 31 */     pst.executeUpdate();
/* 32 */     System.out.println("Saved Encode.");
/*    */   }
/*    */   
/*    */   public void saveCheck(JSONObject jsonObject) throws Exception {
/* 36 */     String sql = "insert into establish(type,vid,t) values(?,?,current_date())";
/* 37 */     this.conn = ConnectUtil.getInstance();
/* 38 */     PreparedStatement pst = this.conn.prepareStatement(sql);
/* 39 */     pst.setString(1, jsonObject.getString("type"));
/* 40 */     pst.setString(2, jsonObject.getString("vid"));
/* 41 */     pst.executeUpdate();
/* 42 */     System.out.println("Saved check.");
/*    */   }
/*    */ }


/* Location:              C:\Users\lenovo\Desktop\demo-0.0.1-SNAPSHOT.jar!\BOOT-INF\classes\com\example\demo\dao\DBManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */