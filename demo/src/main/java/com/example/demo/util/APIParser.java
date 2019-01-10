package com.example.demo.util;

import com.example.demo.api.MethodStatus;
import com.example.demo.api.ParamStatus;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class APIParser {
	/**
	 * TODO: 1.总共有多少个API类可以写在properties中来读取,自动化switch配置
	 *  2.反射读取所有变量,方法名
	 *  3.与状态类ParamStatus一起交给MemberHandler,MemberHandler返回拿到的reqParam,同时初始化好ParamStatus
	 *  4.与状态类MethodStatus一起交给MethodHandler,MethodHandler返回拿到的reqParam,同时初始化好MethodStatus
	 *  5.根据状态进行:   -计算Sign 和 计算重载的方法的参数         其中根据标记令哪些参数用默认值,哪些参数用传参(亦可批量去properties读取  ---若采用properties则需要syso提示一次会用到哪些参数)
	 *                  -根据 是 POST 还是 GET,调用setGET()/setPOST()       其中setGET是替换参数  setPost是将参数丢入entity
	 *                  -发起请求
	 */

	private String secretKey = "";
	private Class targetClass = null;
	private List<String> params = new LinkedList<>();
	private List<String> methods = new LinkedList<>();
	private ParamStatus paramStatus = new ParamStatus();
	private MethodStatus methodStatus = new MethodStatus();

	public static void main(String[] args) {
		try {
			new APIParser().Parse();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @Description: 目前测试主入口 日后需整合逻辑
	 */
	public void Parse() throws IllegalAccessException, InstantiationException, ClassNotFoundException {
		this.ParseInit();
		this.ParseProcessing();
		System.out.println(this.getSignDefault());
	}

	/**
	 * @Description: Step 2,3,4
	 */
	public void ParseInit() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
		this.targetClass = Class.forName("com.example.demo.api.GetByIdTEST");
		//获取自定字段放入params
		Field[] fields = targetClass.getDeclaredFields();
		Arrays.stream(fields).forEach((field) -> this.params.add(field.getName()));
		//获取自定方法放入methods
		Method[] methods = targetClass.getDeclaredMethods();
		Arrays.stream(methods).forEach((method -> this.methods.add(method.getName())));
		//交给ParamHandler
		this.paramStatus.ParamHandler(this.params);
		//交给methodHandler
		this.methodStatus.MethodHandler(this.methods, this.paramStatus);

	}

	/**
	 * @Description: Step 5
	 */
	public void ParseProcessing() {
		/**@TODO:   -doGet还是doPost                                                                                      用不同方法,一个setEntity 一个算url
		 *          -若需要自定参数 则(读properties/cmd输入)   不需读取默认值                                             目前假设全用默认值(只有默认值方法)
		 *              -读默认值则ifDefault   计算sign:sign的默认计算方法   NoNeed 则不执行  sign/hash 则执行对应方法    需要计算sign则计算 不需要则留为null
		 */

		//参数键值对初始化
		Map<String, String> NVP = new LinkedHashMap<>();
		if (this.paramStatus.useDefaultValue) {
			//TODO:重做ParamStatus以存储值?   暂时用Map NVP代替了
			this.paramStatus.params.stream().forEach((p) -> NVP.put(p, getDefaultValue(p)));
		}else {
		}

		if (!this.methodStatus.NoNeedForSign){
			//查找sign算法有没被重载
			if(this.methodStatus.methods.contains("sign")){
				//若sign被重载
			}else {
				//默认sign算法
				getSignDefault();
			}
		}



	}

	/**
	 * @Description: 若需要, 按默认方式拼接sign
	 */
	private String getSignDefault() {
		/**
		 * @Description:  @TODO:所有参数最后加上secretkey再进行sha1
		 *                  Eg:sha1('cataid='.$cataid.'&JSONRPC='.$JSONRPC.'&writetoken='.$writetoken.$secretkey)
		 */
		StringBuilder plain = new StringBuilder();
		this.paramStatus.params.stream().sorted().forEach((p) -> getDefaultValue(p, plain));
		plain.append(secretKey);

		return sha1(plain.toString());
	}

	/**
	* @Description:  获得name参数的对应默认值,给计算DefaultSign用的
	* @Param: [name, plain]
	* @return: void
	*/
	private void getDefaultValue(String name, StringBuilder plain) {
		PropertyDescriptor descriptor = null;
		try {
			//取得方法
			descriptor = new PropertyDescriptor(name, targetClass);
			Method method = descriptor.getReadMethod();
			//执行方法
			Object value = method.invoke(targetClass.newInstance());
			if (value.equals("") || value.equals(null) || null == value) {
				;
			} else if (plain.length() != 0) {
				plain.append("&" + name + "=" + value);
			} else {
				plain.append("" + name + "=" + value);
			}
			System.out.println(name + " " + value);

		} catch (IntrospectionException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		}
	}

	/**
	* @Description: getSign
	 * @param
	*/


	/**
	* @Description: 获取所有参数对应默认值
	*/
	private String getDefaultValue(String name) {
		PropertyDescriptor descriptor = null;
		try {
			//取得方法
			descriptor = new PropertyDescriptor(name, targetClass);
			Method method = descriptor.getReadMethod();
			//执行方法
			Object value = method.invoke(targetClass.newInstance());
			return String.valueOf(value);
		} catch (IntrospectionException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		}
		return  null;
	}

	/**
	 * @Description: sha1加密
	 */
	private String sha1(String plain) {
		MessageDigest digest = null;
		try {
			digest = MessageDigest.getInstance("SHA-1");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		digest.update(plain.getBytes(Charset.forName("UTF-8")));
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

}
