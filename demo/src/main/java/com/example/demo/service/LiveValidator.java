package com.example.demo.service;

import java.nio.charset.Charset;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.example.demo.util.InfoLogger;
import org.apache.commons.codec.digest.DigestUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
* @Description:  两种验证方式secretkey不同，故有两个init版本
* @Author: LJH
*/
@Component
public class LiveValidator {
	private String ts = null;
	private String userid = null;
	private String id = null;
	private String secretkey = null;
	private String token = null;
	private String sign = null;
	private String nickname = "Ti";
	private String url = null;
	private String marqueeName = "test";
	private String avatar = "http://live.polyv.net/assets/images/avatars/9avatar.jpg";
	private int status = 1;

	/**
	* @Description:  自定义授权
	 * 较多的system.out.println,用以测试查看输出结果,应删除
	* @Param: [req]
	* @return: java.lang.String
	* @Author: LJH
	*/
	public String getSDCallBack(HttpServletRequest req) {
		//初始化参数
		initSD(req);
		System.out.println("========inside sd========");
		System.out.println("sign:"+sign);
		System.out.println("token:"+token);
		//若接收到的sign 与 拼接计算后的token 相等则输出
		if (sign.equals(token)) {
			String tmp = secretkey + id + secretkey + ts + secretkey + userid;
			sign = DigestUtils.md5Hex(tmp.getBytes(Charset.forName("UTF-8")));
			String res = url + "?userid=" + userid + "&nickname=" + nickname + "&marqueeName=" + marqueeName
					+ "&avatar=" + avatar + "&ts=" + ts + "&sign=" + sign;
			System.out.println("res:"+res);
			String finalRes = "<script language='javascript' type='text/javascript'>location.href='"+res+"'</script>";
			System.out.println("finalRes:"+finalRes);
			return finalRes;
		}
		//错误则返回error(自定义)
		System.out.println("error");
		return "error";
	}

	/**
	* @Description: 外部授权
	* @Param: [req]
	* @return: java.lang.String
	* @Author: LJH
	*/
	public String getOVCallBack(HttpServletRequest req) {
		//初始化参数
		initOV(req);
		JSONObject json = new JSONObject();
		System.out.println("========inside ov========");
		System.out.println("sign:"+sign);
		System.out.println("token:"+token);
		//若接收到的token 与 拼接计算后的sign 不等则输出
		if (!sign.equals(token)) {
			json.put("status", 0);
			json.put("errorUrl", "http://www.baidu.com");
		} else {
			//相等则输出
			json.put("status", 1);
			json.put("userid", userid);
			json.put("nickname", nickname);
			json.put("avatar", avatar);
			System.out.println(json);
			return json.toString();
		}
		System.out.println(json);
		return json.toString();
	}

	/**
	* @Description:  初始化外部授权的参数
	* @Param: [req]
	* @return: void
	* @Author: LJH
	*/
	private void initOV(HttpServletRequest req) {
		System.out.println("========inside initOV========");
		//此处填入外部授权的secretKey
		secretkey = "em31gCSY1h";
		System.out.println("set secretkey:"+secretkey);
		//获取req参数
		token = req.getParameter("token");
		System.out.println("set token:"+token);
		ts = req.getParameter("ts");
		System.out.println("set ts:"+ts);
		userid = req.getParameter("userid");
		System.out.println("set userid:"+userid);
		//拼接计算sign
		String plain = secretkey + userid + secretkey + ts;
		System.out.println("set plain:"+plain);
		sign = DigestUtils.md5Hex(plain.getBytes(Charset.forName("UTF-8")));
		System.out.println("set sign:"+sign);
	}

	/**
	* @Description:  初始化自定义授权的参数
	* @Param: [req]
	* @Author: LJH
	*/
	private void initSD(HttpServletRequest req) {
		System.out.println("========inside initSD========");
		//此处填入自定义授权的SecretKey
		secretkey = "KWWXssNYBK";
		System.out.println("set secretkey:"+secretkey);
		//获取req参数
		sign = req.getParameter("sign");
		System.out.println("set sign:"+sign);
		ts = req.getParameter("ts");
		System.out.println("set ts:"+ts);
		id = req.getParameter("id");
		System.out.println("set id:"+id);
		url = req.getParameter("url");
		System.out.println("set url:"+url);
		userid = "eciyhturt8";
		System.out.println("set userid:"+userid);
		//拼接计算token
		String plain = secretkey + id + secretkey + ts;
		System.out.println("set plain:"+plain);
		token = DigestUtils.md5Hex(plain.getBytes(Charset.forName("UTF-8")));
		System.out.println("set token:"+token);
	}

	/**
	* @Description:  客户接入外部授权案例
	* @Param: [request, response]
	* @return: java.lang.String
	* @Author: LJH
	*/
	public String outerValidate(ServletRequest request, ServletResponse response) throws Exception {
        try {
        	secretkey = "em31gCSY1h";

        	//获得参数
            String userid = request.getParameter("userid");
            //模拟用户从数据库查询用户id
            this.userid = request.getParameter("userid");
            String token = request.getParameter("token");
            System.out.println("token:"+token);
            String ts = request.getParameter("ts");

            //判断userid是否合法
            if (userid != null && String.valueOf(userid).equals(this.userid)) {
            	//计算sign
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(secretkey).append(userid).append(secretkey).append(ts);
                String signSource = stringBuilder.toString();
                System.out.println("plain:"+signSource);
                String sign = DigestUtils.md5Hex(signSource.getBytes(Charset.forName("UTF-8")));
                System.out.println("sign:"+sign);

                //返回数据
                response.setCharacterEncoding("UTF-8");
                response.setContentType("application/json; charset=utf-8");
                if (sign.equals(token)) {
                    response.getWriter().append("{\"status\":1,\"userid\":\"" + userid + "\",\"nickname\":\"" + nickname + "\",\"avatar\":\"" + avatar + "\"}");
                    System.out.println("{\"status\":1,\"userid\":\"" + userid + "\",\"nickname\":\"" + nickname + "\",\"avatar\":\"" + avatar + "\"}");
                } else {
                    response.getWriter().append("{\"status\":0,\"errorUrl\":\"\"}");
                    System.out.println("{\"status\":0,\"errorUrl\":\"\"}");
                }

            } else {
                response.getWriter().append("{\"status\":0,\"errorUrl\":\"\"}");
                System.out.println("{\\\"status\\\":0,\\\"errorUrl\\\":\\\"\\\"}");
            }

        } catch (Exception e) {
        }
        return null;
    }
}
