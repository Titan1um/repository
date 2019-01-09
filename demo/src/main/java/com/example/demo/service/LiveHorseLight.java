package com.example.demo.service;

import com.example.demo.util.JSonObject;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.Charset;

/**
 * @Description: 此处用了重写过的JsonObject，用LinkedHashMap代替了HashMap以保证返回参数的顺序(方便调试查看，不推荐使用)，
 * 不使用则改声明中的JSonObject为org的json即可
 * @Author: LJH
 */
@Component
public class LiveHorseLight {
	private String vid = null;
	private String uid = null;
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

	/**
	 * @Description: LiveHorseLight主入口, 从req获取参数并设置, 执行计算返回json
	 * @Author: LJH
	 */
	public String getJsonString(HttpServletRequest req) {
		//接受req参数并设置
		setVid(req.getParameter("vid"));
		setCode(req.getParameter("code"));
		setT(req.getParameter("t"));
		setUid(req.getParameter("uid"));
		String prefix = req.getParameter("callback");
		setUsername("Ti");
		//getJson
		JSonObject jsonpObject = getJson();
		//若有则处理jsonp
		String reString = null;
		if (null == prefix) {
			reString = jsonpObject.toString();
		} else {
			reString = prefix + "(" + jsonpObject.toString() + ")";
		}
		System.out.println(reString);
		return reString;
	}


	/**
	 * @Description: LiveHorseLight主逻辑
	 * 计算sign，并把参数都包装如jsonObject，返回
	 * @return: com.example.demo.util.JSonObject
	 * @Author: LJH
	 */
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

	/**
	 * @Description: 授权入口
	 * @return: com.example.demo.util.JSonObject
	 * @Author: LJH
	 */
	public JSonObject getJsonForValidateOnly() {
		getSignForValidateOnly();
		JSonObject object = new JSonObject();
		//status可判断用户是否为合法用户后再返回
		object.put("status", 1);
		object.put("username", this.username);
		object.put("sign", this.sign);
		return object;
	}

	/**
	 * @Description: 拼接计算并返回授权sign
	 * @return: java.lang.String
	 * @Author: LJH
	 */
	public String getSignForValidateOnly() {
		String plain = "vid=" + vid + "&secretkey=" + secretKey + "&username=" + username + "&code=" + code + "&status=" + status + "&t=" + t;
		this.sign = DigestUtils.md5Hex(plain.getBytes(Charset.forName("UTF-8")));
		return sign;
	}

	/**
	 * @Description: 拼接计算并返回跑马灯sign
	 * @return: java.lang.String
	 * @Author: LJH
	 */
	public String getSign() {
		String plain = "vid=" + vid + "&uid=" + uid + "&username=" + username + "&code=" + code + "&t=" + t + "&msg=" + msg + "&fontSize=" + fontSize + "&fontColor=" + fontColor
				+ "&speed=" + speed + "&filter=" + filter + "&setting=" + setting + "&alpha=" + alpha + "&filterAlpha=" + filterAlpha + "&filterColor=" + filterColor + "&blurX=" + blurX
				+ "&blurY=" + blurY + "&interval=" + interval + "&lifeTime=" + lifeTime + "&tweenTime=" + tweenTime + "&strength=" + strength + "&show=" + show;

		System.out.println(plain);
		this.sign = DigestUtils.md5Hex(plain.getBytes(Charset.forName("UTF-8")));
		return this.sign;
	}

	/**
	 * @Description: 用main做测试类，在此手动填入参数，测试正确的返回sign
	 * @return: void
	 * @Author: LJH
	 */
	public static void main(String[] args) {
		LiveHorseLight horseLight = new LiveHorseLight();
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

	public void setUid(String uid) {
		this.uid = uid;
	}

	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}
}
