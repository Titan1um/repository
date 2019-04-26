package com.jun.kxpwd.dao;

import com.jun.kxpwd.util.ConnectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @description:
 * @create: 2019-04-26 16:25
 * @author: jun
 */
@Component
public class DBManager {

	private Connection conn;

	@Autowired
	public DBManager(ConnectUtil connectUtil) {
		try {
			this.conn = connectUtil.getInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void save(String id, String pwd) throws SQLException {
		String sql_query = "select * from kxpwd where id = ? & pwd = ?";
		PreparedStatement pst_query = conn.prepareStatement(sql_query);
		pst_query.setString(1, id);
		pst_query.setString(2, pwd);
		ResultSet res_query = pst_query.executeQuery();
		boolean isContain = false;
		if (res_query.next()) {
			isContain = true;
		}

		if (isContain == false) {
			String sql = "insert into kxpwd values(?,?)";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, id);
			pst.setString(2, pwd);
			pst.execute();

		}
	}

}
