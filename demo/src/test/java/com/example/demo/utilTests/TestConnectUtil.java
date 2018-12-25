package com.example.demo.utilTests;

import com.example.demo.util.ConnectUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @Description: ensure that read the correct information from application.properties
 * @Author: LJH
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestConnectUtil {

	@Value("${DB_HOST}")
	private String host;
	@Value("${DB_DBNAME}")
	private String dbName;
	private String url="jdbc:mysql://" + host + "/" + dbName + "?useUnicode=true&characterEncoding=utf8";
	private String driver = "com.mysql.jdbc.Driver";
	@Value("${DB_USER}")
	private String user;
	@Value("${DB_PWD}")
	private String password;

	@Autowired
	ConnectUtil connectUtil;

	@Resource
	private Environment env;

	@Test
	public void testParamReading() {
		System.out.println(host);
		System.out.println(dbName);
		System.out.println(url);
		System.out.println(user);
		System.out.println(password);
	}

	@Test
	public void testEnvReading() {
		System.out.println(env.getProperty("msg"));
	}

	@Test
	public void testOtherProperties() {
		//测试其他properties文件能否被读到
		System.out.println(env.getProperty("exist"));
		//不能
	}

	@Test
	public void testConnect() {
		try {
			url = "jdbc:mysql://" + host + "/" + dbName + "?useUnicode=true&characterEncoding=utf8";
			System.out.println(url);
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(url, user, password);
			if (!conn.isClosed()) {
				System.out.println("Succeeded connecting to the Database!");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	@Test
	public void testUtil(){
		try {
			connectUtil.getInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
