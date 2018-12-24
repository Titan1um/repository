package com.example.demo.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectUtil {
	private Connection conn = null;
	@Value("${DB_HOST}")
	private String host;
	@Value("${DB_DBNAME}")
	private String dbName;
	private String url;
	private String driver = "com.mysql.jdbc.Driver";
	@Value("${DB_USER}")
	private String user;
	@Value("${DB_PWD}")
	private String password;

	@Autowired
	private Environment env;

	public ConnectUtil() {
		try {
			host = env.getProperty("DB_HOST");
			dbName = env.getProperty("DB_DBNAME");
			user = env.getProperty("DB_USER");
			password = env.getProperty("DB_PWD");
			url = "jdbc:mysql://" + host + "/" + dbName + "?useUnicode=true&characterEncoding=utf8";
			Class.forName(driver);
			//for test
			System.out.println("connect failure:url=" + url + "\nuser=" + user + "\npassword=" + password);
			//for test
			conn = DriverManager.getConnection(url, user, password);
			if (!conn.isClosed()) {
				System.out.println("Succeeded connecting to the Database!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Connection getInstance()
			throws Exception {
		if ((null != conn) && (!conn.isClosed())) {
			return conn;
		}
		return null;
	}

	@Override
	protected void finalize()
			throws Throwable {
		super.finalize();
		conn.close();
	}
}

