package com.example.demo.service;

import com.example.demo.dao.DBManager;

import java.io.PrintStream;
import java.nio.charset.Charset;
import javax.servlet.http.HttpServletRequest;

import com.example.demo.util.InfoLogger;
import org.apache.commons.codec.digest.DigestUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class CallBack {
	@Autowired
	InfoLogger infoLogger;
	private final String _SECRETKEY_ = "qW4nvoVVi5";
	@Autowired
	private DBManager dbManager;

	/**
	* @Description:  处理回调入口,主要逻辑:判断req.sign的回调类型,打包req参数,调用DBManager.save()
	* @Param: [req]
	* @return: void
	* @Author: LJH
	*/
	public void save(HttpServletRequest req){
		String opt = cmpSign(req);
		try {
			switch (opt) {
				case "upload":
					this.dbManager.saveUpload(saveUpload(req));
					break;
				case "encode":
					this.dbManager.saveEncode(saveEncode(req));
					break;
				case "check":
					this.dbManager.saveCheck(saveCheck(req));
					break;
				case "wrong encrypt":
					System.out.println("Encryption is not right");
					break;
				default:
					System.out.println("error:mtfk.");
			}
		}catch (Exception e){
			infoLogger.log(e.toString());
			e.printStackTrace();
		}

	}

	/**
	* @Description:  判断sign是何种回调
	* @Param: [req]
	* @return: java.lang.String
	* @Author: LJH
	*/
	private String cmpSign(HttpServletRequest req) {
		String sign = req.getParameter("sign");
		String vid = req.getParameter("vid");
		String plain = "upload" + vid + "qW4nvoVVi5";
		plain = DigestUtils.md5Hex(plain.getBytes(Charset.forName("UTF-8")));

		if(sign == null){
			return "wrong encrypt";
		}

		//是否符合上传拼接方式
		if (sign.equals(plain)) {
			return "upload";
		}

		//是否符合审核拼接方式
		String type = req.getParameter("type");
		plain = "manage" + type + vid + "qW4nvoVVi5";
		plain = DigestUtils.md5Hex(plain.getBytes(Charset.forName("UTF-8")));
		if (sign.equals(plain)) {
			return "check";
		}

		//是否符合编码拼接方式
		String df = req.getParameter("df");
		String format = req.getParameter("format");
		plain = "encode" + format + vid + df + "qW4nvoVVi5";
		plain = DigestUtils.md5Hex(plain.getBytes(Charset.forName("UTF-8")));
		if (sign.equals(plain)) {
			return "encode";
		}


		System.out.println(vid);
		System.out.println(sign);
		System.out.println("qW4nvoVVi5");
		return "wrong encrypt";
	}

	/**
	* @Description:  将req参数放入jsonObject
	* @Param: [req]
	* @return: org.json.JSONObject
	* @Author: LJH
	*/
	private JSONObject saveUpload(HttpServletRequest req) throws Exception {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("type", req.getParameter("type"));
		jsonObject.put("vid", req.getParameter("vid"));

		return jsonObject;
	}

	/**
	* @Description: 将req参数放入jsonObject
	* @Param: [req]
	* @return: org.json.JSONObject
	* @Author: LJH
	*/
	private JSONObject saveEncode(HttpServletRequest req) throws Exception {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("format", req.getParameter("format"));
		jsonObject.put("type", req.getParameter("type"));
		jsonObject.put("vid", req.getParameter("vid"));
		jsonObject.put("df", req.getParameter("df"));

		return jsonObject;
	}

	/**
	* @Description: 将req参数放入jsonObject
	* @Param: [req]
	* @return: org.json.JSONObject
	* @Author: LJH
	*/
	private JSONObject saveCheck(HttpServletRequest req) throws Exception {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("vid", req.getParameter("vid"));
		jsonObject.put("type", req.getParameter("type"));

		return jsonObject;
	}
}
