package com.example.demo.dao;

import com.example.demo.util.ConnectUtil;

import java.io.PrintStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.json.JSONObject;

@org.springframework.stereotype.Component
public class DBManager {
	Connection conn = null;

	public void saveUpload(JSONObject jsonObject) {
		String sql = "insert into upload(type,vid,t) values(?,?,current_date())";
		PreparedStatement pst = null;
		try {
			this.conn = ConnectUtil.getInstance();
			pst = this.conn.prepareStatement(sql);
			pst.setString(1, jsonObject.getString("type"));
			pst.setString(2, jsonObject.getString("vid"));
			pst.executeUpdate();
			pst.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != pst) {
					pst.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		System.out.println("Saved upload.");
	}

	public void saveEncode(JSONObject jsonObject) {
		String sql = "insert into encode(type,vid,format,df,t) values(?,?,?,?,current_date())";
		PreparedStatement pst = null;
		try {
			this.conn = ConnectUtil.getInstance();
			pst = this.conn.prepareStatement(sql);
			pst.setString(1, jsonObject.getString("type"));
			pst.setString(2, jsonObject.getString("vid"));
			pst.setString(3, jsonObject.getString("format"));
			pst.setString(4, jsonObject.getString("df"));
			pst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != pst) {
					pst.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Saved Encode.");
	}

	public void saveCheck(JSONObject jsonObject) {
		String sql = "insert into establish(type,vid,t) values(?,?,current_date())";
		PreparedStatement pst = null;
		try {
			this.conn = ConnectUtil.getInstance();
			pst = this.conn.prepareStatement(sql);
			pst.setString(1, jsonObject.getString("type"));
			pst.setString(2, jsonObject.getString("vid"));
			pst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != pst) {
					pst.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Saved check.");
	}

	@Override
	protected void finalize() {
		try {
			super.finalize();
		} catch (Throwable throwable) {
			throwable.printStackTrace();
		}

		if (null == conn) {
			return;
		}
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}

