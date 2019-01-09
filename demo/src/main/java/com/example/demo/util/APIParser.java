package com.example.demo.util;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

public class APIParser {
	/**
	 * 1.总共有多少个API类可以写在properties中来读取,自动化switch配置
	 * 2.反射读取所有变量,方法名
	 * 3.与状态类ParamStatus一起交给MemberHandler,MemberHandler返回拿到的reqParam,同时初始化好ParamStatus
	 * 4.与状态类MethodStatus一起交给MethodHandler,MethodHandler返回拿到的reqParam,同时初始化好MethodStatus
	 * 5.根据状态进行 -计算Sign 和 计算重载的方法的参数         其中根据标记令哪些参数用默认值,哪些参数用传参(亦可批量去properties读取  ---若采用properties则需要syso提示一次会用到哪些参数)
	 *                  -根据 是 POST 还是 GET,调用setGET()/setPOST()       其中setGET是替换参数  setPost是将参数丢入entity
	 *                  -发起请求
	 */

	private String secretKey = "";
	private List<String> params = new LinkedList<>();
	private List<String> methods = new LinkedList<>();

	public static void main(String[] args) {
		try {
			new APIParser().Parse();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @Description: 2.反射读取所有变量, 方法名
	 */
	public void Parse() throws ClassNotFoundException {
		Class targetClass = Class.forName("com.example.demo.api.GetByIdTEST");
		//获取自定字段
		Field[] fields = targetClass.getDeclaredFields();
		for (Field field : fields) {
			this.params.add(field.getName());
		}
		Method[] methods = targetClass.getDeclaredMethods();
		for (Method method : methods) {
			this.methods.add(method.getName());
		}

		System.out.println(this.params);
	}
}
