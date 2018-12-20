/*    */ package com.example.demo.util;
/*    */ 
/*    */ import java.io.PrintStream;
/*    */ import java.sql.Connection;
/*    */ import java.sql.DriverManager;
/*    */ 
/*    */ public class ConnectUtil
/*    */ {
/*  9 */   private static Connection conn = null;
/* 10 */   private static String url = "jdbc:mysql://localhost:3306/jun?useUnicode=true&characterEncoding=utf8";
/* 11 */   private static String driver = "com.mysql.jdbc.Driver";
/* 12 */   private static String user = "root";
/* 13 */   private static String password = "root";
/*    */   
/*    */   static {
/*    */     try {
/* 17 */       Class.forName(driver);
/* 18 */       conn = DriverManager.getConnection(url, user, password);
/* 19 */       if (!conn.isClosed()) {
/* 20 */         System.out.println("Succeeded connecting to the Database!");
/*    */       }
/*    */     } catch (Exception e) {
/* 23 */       e.printStackTrace();
/*    */     }
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public static Connection getInstance()
/*    */     throws Exception
/*    */   {
/* 39 */     if ((null != conn) && (!conn.isClosed()))
/* 40 */       return conn;
/* 41 */     return null;
/*    */   }
/*    */   
/*    */   protected void finalize()
/*    */     throws Throwable
/*    */   {
/* 47 */     super.finalize();
/* 48 */     conn.close();
/*    */   }
/*    */ }


/* Location:              C:\Users\lenovo\Desktop\demo-0.0.1-SNAPSHOT.jar!\BOOT-INF\classes\com\example\demo\util\ConnectUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */