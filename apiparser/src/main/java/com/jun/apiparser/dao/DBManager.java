package com.jun.apiparser.dao;

import com.jun.apiparser.utils.ConnectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Component
public class DBManager {
	@Autowired
	ConnectUtil connectUtil;
	Connection conn;

	public boolean saveDescription(String name,String des){
		try {
			conn = connectUtil.getInstance();
			PreparedStatement pst = conn.prepareStatement("replace into description(name,des) values(?,?);");
			pst.setString(1, name);
			pst.setString(2, des);
			pst.execute();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public String getDescription(String name){
		try{
			conn = connectUtil.getInstance();
			PreparedStatement pst = conn.prepareStatement("select des from description where name=?");
			pst.setString(1, name);
			ResultSet res = pst.executeQuery();
			if(res.next()){
				return res.getString(1);
			}
		}catch (Exception e){
			e.printStackTrace();
			return "";
		}
		return "null";
	}

	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		if (conn != null && !conn.isClosed()) {
			conn.close();
		}
	}
}
