package com.example.demo.service;

import com.example.demo.util.HttpClientUtil;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import com.example.demo.util.InfoLogger;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Description: 提供playsafe生成功能的service
 *              使用PlaySafe工具前需设置sercretKey
 * 	            使用PlaySafe工具时需设置vid ts viewerIp
 *
 * 	            主要函数为getToken() getSignForMobile()
 * @Author: LJH
 */
@Component
public class PlaySafe {
	/**
	 * @TODO: 前端页面带参(vid,PCorMOBILE)访问playSafe接口,接口返回所需类型
	 *          PC {playsafe:}  Mobile {ts: ,sign:}
	 */
	private String userId = "7ca55a3c6f";
	private String secretKey = "qW4nvoVVi5";
	private String videoId = "7ca55a3c6f84422e3c852a2bf5de56ca_7";
	private String ts = null;
	/**
	 * viewerIP 若开发者不想设置,应用一默认值代替
	 */
	private String viewerIp = "";
	private String viewerId = "12345";
	private String viewerName = "testUser";
	private String extraParams = "HTML5";
	private String url = "https://hls.videocc.net/service/v1/token";
	private String sign = "";
	/**
	 * InfoLogger 若不使用则删除声明和在异常中的使用
	 */
	@Autowired
	private InfoLogger infoLogger;


	public static void main(String[] args)
			throws UnknownHostException {
		//测试
		//使用PlaySafe工具前需设置sercretKey
		//使用PlaySafe工具时需设置vid ts viewerIp
		PlaySafe playSafe = new PlaySafe();
		String address = InetAddress.getLocalHost().getHostAddress().toString();
		playSafe.setViewerIp(address);
		playSafe.setTs(String.valueOf(System.currentTimeMillis()));
		playSafe.setVideoId("7ca55a3c6f84422e3c852a2bf5de56ca_7");
		System.out.println("token:" + playSafe.getToken());
	}

	/**
	* @Description:  获取token的入口
	* @Author: LJH
	*/
	public String getToken() {
		//生成sign
		String sign = getSign();
		//将用户信息包装为entity
		HttpEntity entity = getData(sign);
		//http请求工具类
		HttpClientUtil client = HttpClientUtil.getInstance();
		//HttpClientUtil.getToken 以post方式将entity发送到指定url
		String token = client.getToken(this.url, entity);

		JSONObject json = new JSONObject(token);
		json = (JSONObject) json.get("data");
		token = json.getString("token");
		return token;
	}


	/**
	* @Description:  返回PC端播放token请求所需的Sign
	* @return: java.lang.String
	* @Author: LJH
	*/
	private String getSign() {
		if (null == this.ts) {
			this.ts = String.valueOf(System.currentTimeMillis());
		}
		String tmp = "extraParams" + this.extraParams + "ts" + this.ts + "userId" + this.userId + "videoId" + this.videoId + "viewerId" + this.viewerId + "viewerIp" + this.viewerIp + "viewerName" + this.viewerName;

		String plain = this.secretKey + tmp + this.secretKey;
		this.sign = DigestUtils.md5Hex(plain.getBytes(Charset.forName("UTF-8")));
		this.sign = this.sign.toUpperCase();
		return this.sign;
	}

	/**
	* @Description:  返回手机端观看时需要的sign
	* @return: java.lang.String
	* @Author: LJH
	*/
	public String getSignForMobile() {
		if (null == this.ts) {
			this.ts = String.valueOf(System.currentTimeMillis());
		}
		String plain = this.secretKey + this.videoId + this.ts;
		this.sign = DigestUtils.md5Hex(plain.getBytes(Charset.forName("UTF-8")));
		return this.sign;
	}


	/**
	* @Description:  处理请求参数到entity中
	 *                  并返回一个entity
	* @Param: [sign]
	* @return: org.apache.http.HttpEntity
	*/
	private HttpEntity getData(String sign) {
		List list = new ArrayList();
		list.add(new BasicNameValuePair("userId", this.userId));
		list.add(new BasicNameValuePair("videoId", this.videoId));
		list.add(new BasicNameValuePair("ts", this.ts));
		list.add(new BasicNameValuePair("viewerIp", this.viewerIp));
		list.add(new BasicNameValuePair("viewerName", this.viewerName));
		list.add(new BasicNameValuePair("extraParams", this.extraParams));
		list.add(new BasicNameValuePair("viewerId", this.viewerId));
		list.add(new BasicNameValuePair("sign", sign));
		HttpEntity entity = null;
		try {
			entity = new UrlEncodedFormEntity(list);
		} catch (Exception e) {
			e.printStackTrace();
			infoLogger.log(e.toString());
		}


		return entity;
	}

	//下均为getter setter

	public void setViewerIp(String ip) {
		this.viewerIp = ip;
		if (null == ip) {
			this.viewerIp = "";
		}
	}

	public String getTs() {
		if(null == this.ts) {
			this.ts = String.valueOf(System.currentTimeMillis());
		}
		return this.ts;
	}


	public String getUserId() {
		return this.userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
		if (null == userId) {
			this.userId = "";
		}
	}


	public String getSecretKey() {
		return this.secretKey;
	}


	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
		if (null == secretKey) {
			this.secretKey = "";
		}
	}


	public String getVideoId() {
		return this.videoId;
	}


	public void setVideoId(String videoId) {
		this.videoId = videoId;
		if (null == videoId) {
			this.videoId = "";
		}
	}


	public String getViewerId() {
		return this.viewerId;
	}


	public void setViewerId(String viewerId) {
		this.viewerId = viewerId;
		if (null == viewerId) {
			this.viewerId = "";
		}
	}


	public String getViewerName() {
		return this.viewerName;
	}


	public void setViewerName(String viewerName) {
		this.viewerName = viewerName;
		if (null == viewerName) {
			this.viewerName = "";
		}
	}


	public String getExtraParams() {
		return this.extraParams;
	}


	public void setExtraParams(String extraParams) {
		this.extraParams = extraParams;
		if (null == extraParams) {
			this.extraParams = "";
		}
	}


	public String getUrl() {
		return this.url;
	}


	public void setUrl(String url) {
		if (null == url) {
			this.url = "";
		}
	}


	public String getViewerIp() {
		return this.viewerIp;
	}


	public void setTs(String ts) {
		this.ts = ts;
	}
}

