package com.example.demo.util;

import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectUtil {
	private static Connection conn = null;
	private static String url = "jdbc:mysql://localhost:3306/jun?useUnicode=true&characterEncoding=utf8";
	private static String driver = "com.mysql.jdbc.Driver";
	private static String user = "root";
	private static String password = "root";

	static {
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
			if (!conn.isClosed()) {
				System.out.println("Succeeded connecting to the Database!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public static Connection getInstance()
			throws Exception {
		if ((null != conn) && (!conn.isClosed()))
			return conn;
		return null;
	}

	@Override
	protected void finalize()
			throws Throwable {
		super.finalize();
		conn.close();
	}
}

