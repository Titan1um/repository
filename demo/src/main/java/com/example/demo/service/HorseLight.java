package com.example.demo.service;

import com.example.demo.util.JSonObject;

import java.io.PrintStream;
import java.nio.charset.Charset;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Component;


@Component
public class HorseLight {
	private String vid = null;
	private String secretKey = "qW4nvoVVi5";
	private String username = "jun";
	private String code = null;
	private int status = 1;
	private String t = null;


	private String sign = null;
	private String msg = "Errormessage!";

	private String fontSize = "40";
	private String fontColor = "0xFFE900";
	private String speed = "200";
	private String filter = "on";
	private String setting = "3";
	private String alpha = "1";
	private String filterAlpha = "1";
	private String filterColor = "0x3914AF";
	private String blurX = "2";
	private String blurY = "2";
	private String tweenTime = "1";
	private String interval = "5";
	private String lifeTime = "3";
	private String strength = "4";
	private String show = "on";


	public String getSign() {
		String plain = "vid=" + this.vid + "&secretkey=" + this.secretKey + "&username=" + this.username + "&code=" + this.code + "&status=" + this.status + "&t=" + this.t;


		System.out.println(plain);
		this.sign = DigestUtils.md5Hex(plain.getBytes(Charset.forName("UTF-8")));
		return this.sign;
	}

	public JSonObject getJson() {
		getSign();
		JSonObject object = new JSonObject();
		object.put("status", 1);
		object.put("username", this.username);
		object.put("sign", this.sign);
		object.put("msg", this.msg);
		object.put("fontSize", this.fontSize);
		object.put("fontColor", this.fontColor);
		object.put("speed", this.speed);
		object.put("filter", this.filter);
		object.put("setting", this.setting);
		object.put("alpha", this.alpha);
		object.put("filterAlpha", this.filterAlpha);
		object.put("filterColor", this.filterColor);
		object.put("blurX", this.blurX);
		object.put("blurY", this.blurY);
		object.put("tweenTime", this.tweenTime);
		object.put("interval", this.interval);
		object.put("lifeTime", this.lifeTime);
		object.put("strength", this.strength);
		object.put("show", this.show);
		return object;
	}


	public static void main(String[] args) {
		HorseLight horseLight = new HorseLight();
		horseLight.setT("143020010115550947");
		horseLight.setCode("abc");
		horseLight.setVid("8f8482aaab11dd5f45f183a9192a04c5_8");
		horseLight.setUsername("suki");
		horseLight.setSecretKey("AiDQw1mAmi");
		System.out.println("=========s=i=g=n==" + horseLight.getSign() + "=============");
	}

	public void setVid(String vid) {
		this.vid = vid;
		if (null == vid) {
			this.vid = "";
		}
	}

	public void setCode(String code) {
		this.code = code;
		if (null == code) {
			this.code = "";
		}
	}

	public void setT(String t) {
		this.t = t;
		if (null == t) {
			this.t = "";
		}
	}

	public void setUsername(String username) {
		this.username = username;
		if (null == username) {
			this.username = "";
		}
	}

	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}
}
