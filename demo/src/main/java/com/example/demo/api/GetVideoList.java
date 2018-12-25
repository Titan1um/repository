package com.example.demo.api;

import java.io.IOException;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import com.example.demo.util.HttpClientUtil;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;


public class GetVideoList {
	private String catatree = "1";
	private String url = "http://api.polyv.net/v2/video/7ca55a3c6f/get-new-list";

	private String userid = "7ca55a3c6f";
	private String secretKey = "qW4nvoVVi5";
	private String ptime = String.valueOf(System.currentTimeMillis());
	private String sign = "";
	private String pageNum = "1";
	private String numPerPage = "10000";
	private String startDate = "2018-11-14";
	private String endDate = "2018-11-27";
	private String format = "json";


	public static void main(String[] args) {
		try {
			new GetVideoList().getVideoListInPost();
			new GetVideoList().getAllReadyVideo();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	* @Description:  获取所有视频
	* @return: java.lang.String
	* @Author: LJH
	*/
	public String getVideoList() {
		return getVideoListInGet();
	}

	public String getVideoListInGet() {
		init();
		HttpClientUtil client = HttpClientUtil.getInstance();
		String res = client.getVideoList(this.url, this.sign);
		return res;
	}

	public String getVideoListInPost() throws Exception {
		HttpClientUtil client = HttpClientUtil.getInstance();
		init2();
		ArrayList nvps = new ArrayList();
		nvps.add(new BasicNameValuePair("catatree", this.catatree));
		nvps.add(new BasicNameValuePair("endDate", this.endDate));
		nvps.add(new BasicNameValuePair("format", this.format));
		nvps.add(new BasicNameValuePair("numPerPage", this.numPerPage));
		nvps.add(new BasicNameValuePair("pageNum", this.pageNum));
		nvps.add(new BasicNameValuePair("ptime", this.ptime));
		nvps.add(new BasicNameValuePair("startDate", this.startDate));
		nvps.add(new BasicNameValuePair("sign", this.sign));


		String res = client.getToken(this.url, new UrlEncodedFormEntity(nvps, "utf-8"));

		return res;
	}


	private void init() {
		String reqStr = "catatree=" + this.catatree + "&endDate=" + this.endDate + "&format=" + this.format + "&numPerPage=" + this.numPerPage + "&pageNum=" + this.pageNum + "&ptime=" + this.ptime + "&startDate=" + this.startDate;

		this.url = (this.url + "?" + reqStr);
		try {
			this.sign = encrypt(reqStr + this.secretKey);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}


	private void init2() {
		String reqStr = "catatree=" + this.catatree + "&endDate=" + this.endDate + "&format=" + this.format + "&numPerPage=" + this.numPerPage + "&pageNum=" + this.pageNum + "&ptime=" + this.ptime + "&startDate=" + this.startDate + this.secretKey;

		try {
			this.sign = encrypt(reqStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public String encrypt(String decrypt)
			throws NoSuchAlgorithmException {
		MessageDigest digest = MessageDigest.getInstance("SHA-1");
		digest.update(decrypt.getBytes(Charset.forName("UTF-8")));
		byte[] messageDigest = digest.digest();

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

	public String getUserid() {
		return this.userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getPtime() {
		return this.ptime;
	}

	public void setPtime() {
		long currentTime = System.currentTimeMillis();
		currentTime /= 1000L;
		String cString = String.valueOf(currentTime);
	}


	public void temp(String url, HttpEntity entity)
			throws Exception {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);


		httpPost.setEntity(entity);
		System.out.println(entity);
		try {
			CloseableHttpResponse response = httpClient.execute(httpPost);

			HttpEntity rEntity = response.getEntity();
			String content = EntityUtils.toString(rEntity);
			System.out.println(content);


			response.close();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			httpClient.close();
		}
	}

	/**
	* @Description:  获取所有已经就绪的视频
	* @return: java.lang.String
	* @Author: LJH
	*/
	public String getAllReadyVideo() {
		String string = getVideoList();
		JSONObject jsonObject = new JSONObject(string);
		JSONArray jsonArray = jsonObject.getJSONArray("data");
		int length = jsonArray.length();
		for (int i = 0; i < length; i++) {
			if (jsonArray.getJSONObject(i).getString("status").equals("61")) {
				System.out.println(jsonArray.getJSONObject(i));

			}
		}
		return jsonObject.toString();
	}
}

