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
	private String username = "版权所有 ©蓝铅笔 www.lanqb.com ID：15068746901正在观看";
	private String code = null;
	private String t = null;


	private String sign = null;
	//	private String msg = "Errormessage!";
	private String msg = "Hello!";
	private String fontSize = "40";
	private String fontColor = "0x000000";
	private String speed = "200";
	private String filter = "off";
	private String setting = "1";
	private String alpha = "1";
	private String filterAlpha = "1";
	private String filterColor = "0x000000";
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
		object.put("show", this.show);
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
		object.put("interval", this.interval);
		object.put("lifeTime", this.lifeTime);
		object.put("tweenTime", this.tweenTime);
		object.put("strength", this.strength);
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
		String plain = "vid=" + vid + "&secretkey=" + secretKey + "&username=" + username + "&code=" + code + "&status=" + 1 + "&t=" + t;
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
		horseLight.setUsername("版权所有 ©蓝铅笔 www.lanqb.com ID：15068746901正在观看");
		horseLight.setT("1234");
		horseLight.setCode("1234");
		horseLight.setVid("1234");
		horseLight.setUid("1234");
		horseLight.setMsg("error");
		horseLight.setFontSize("12");
		horseLight.setFontColor("0xffffff");
		horseLight.setSpeed("1500");
		horseLight.setFilter("on");
		horseLight.setSetting("1");
		horseLight.setAlpha("0.5");
		horseLight.setFilterAlpha("1");
		horseLight.setFilterColor("0xffffff");
		horseLight.setBlurX("2");
		horseLight.setBlurY("2");
		horseLight.setInterval("3");
		horseLight.setLifeTime("60");
		horseLight.setTweenTime("1");
		horseLight.setStrength("4");
		horseLight.setShow("on");
		System.out.println(horseLight.getJson());


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

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public void setFontSize(String fontSize) {
		this.fontSize = fontSize;
	}

	public void setFontColor(String fontColor) {
		this.fontColor = fontColor;
	}

	public void setSpeed(String speed) {
		this.speed = speed;
	}

	public void setFilter(String filter) {
		this.filter = filter;
	}

	public void setSetting(String setting) {
		this.setting = setting;
	}

	public void setAlpha(String alpha) {
		this.alpha = alpha;
	}

	public void setFilterAlpha(String filterAlpha) {
		this.filterAlpha = filterAlpha;
	}

	public void setFilterColor(String filterColor) {
		this.filterColor = filterColor;
	}

	public void setBlurX(String blurX) {
		this.blurX = blurX;
	}

	public void setBlurY(String blurY) {
		this.blurY = blurY;
	}

	public void setTweenTime(String tweenTime) {
		this.tweenTime = tweenTime;
	}

	public void setInterval(String interval) {
		this.interval = interval;
	}

	public void setLifeTime(String lifeTime) {
		this.lifeTime = lifeTime;
	}

	public void setStrength(String strength) {
		this.strength = strength;
	}

	public void setShow(String show) {
		this.show = show;
	}

	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}
}
