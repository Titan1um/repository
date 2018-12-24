package com.example.demo.dao;

import com.example.demo.util.ConnectUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.example.demo.util.InfoLogger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

/**
* @Description:  其中三个saveXXX主要目前用作保存回调
* @Author: LJH
*/
@org.springframework.stereotype.Component
public class DBManager {
	@Autowired
	InfoLogger infoLogger;
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
			infoLogger.log(e.toString());
		} finally {
			try {
				if (null != pst) {
					pst.close();
				}
			} catch (SQLException e) {
				infoLogger.log(e.toString());
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
			infoLogger.log(e.toString());
		} finally {
			try {
				if (null != pst) {
					pst.close();
				}
			} catch (SQLException e) {
				infoLogger.log(e.toString());
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
			infoLogger.log(e.toString());
		} finally {
			try {
				if (null != pst) {
					pst.close();
				}
			} catch (SQLException e) {
				infoLogger.log(e.toString());
			}
		}
		System.out.println("Saved check.");
	}

	@Override
	protected void finalize() {
		try {
			super.finalize();
		} catch (Throwable throwable) {
			infoLogger.log(throwable.toString());
		}

		if (null == conn) {
			return;
		}
		try {
			conn.close();
		} catch (SQLException e) {
			infoLogger.log(e.toString());
			e.printStackTrace();
		}

	}

}

