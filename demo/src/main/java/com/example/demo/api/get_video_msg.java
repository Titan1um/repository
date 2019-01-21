package com.example.demo.api;

import com.example.demo.util.ConnectUtil;
import com.example.demo.util.HttpClientUtil;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class get_video_msg {

	public static void main(String[] args) throws UnsupportedEncodingException {
		HttpClientUtil clientUtil = HttpClientUtil.getInstance();

		String sercretKey = "qW4nvoVVi5";
		String vid = "7ca55a3c6fabae30e22dbeecdaa5fafe_7";
		String userid = "7ca55a3c6f";
		String format = "json";
		String ptime = String.valueOf(System.currentTimeMillis());

		String sign;
		String plain;
		plain = "format"+format+"ptime="+ptime+"&vid="+vid+sercretKey;
		sign = sha1(plain);

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("vid", vid);
		jsonObject.put("ptime", ptime);
		jsonObject.put("sign", sign);
		jsonObject.put("format", format);

		StringEntity entity = new StringEntity(jsonObject.toString());
		entity.setContentType("application/json");
		HttpPost httpPost = new HttpPost("http://api.polyv.net/v2/video/7ca55a3c6f/get-video-msg");
		httpPost.setEntity(entity);
		System.out.println(clientUtil.doPost(httpPost));


	}

	private static String sha1(String plain) {
		MessageDigest digest = null;
		try {
			digest = MessageDigest.getInstance("SHA-1");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		byte[] messageDigest = "".getBytes(Charset.forName("UTF-8"));
		if(digest != null) {
			digest.update(plain.getBytes(Charset.forName("UTF-8")));
			messageDigest = digest.digest();
		}

		StringBuffer hexString = new StringBuffer();
		for (int i = 0; i < messageDigest.length; i++) {
			String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
			if (shaHex.length() < 2) {
				hexString.append(0);
			}
			hexString.append(shaHex);
		}
		return hexString.toString().toUpperCase();
	}
}
