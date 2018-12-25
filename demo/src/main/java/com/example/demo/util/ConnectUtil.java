package com.example.demo.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.sql.Connection;
import java.sql.DriverManager;

@Component
public class ConnectUtil {
	private Connection conn = null;
	private String host="127.0.0.1";
	private String dbName="jun";
	private String url;
	private String driver = "com.mysql.jdbc.Driver";
	private String user="root";
	private String password="root";
	@Resource
	Environment env;

	public void Connect() {
		try {
			host = env.getProperty("DB_HOST");
			dbName = env.getProperty("DB_DBNAME");
			user = env.getProperty("DB_USER");
			password = env.getProperty("DB_PWD");
			url = "jdbc:mysql://" + host + "/" + dbName + "?useUnicode=true&characterEncoding=utf8";
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
			if (!conn.isClosed()) {
				System.out.println("Succeeded connecting to the Database!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Connection getInstance() throws Exception {
		Connect();
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

