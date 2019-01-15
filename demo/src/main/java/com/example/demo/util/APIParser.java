package com.example.demo.util;

import com.example.demo.api.MethodStatus;
import com.example.demo.api.ParamStatus;
import org.apache.http.HttpEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class APIParser {
	/**
	 * TODO: 1.总共有多少个API类可以写在properties中来读取(util.GetAllAPI已完成),自动化switch配置
	 * FINISH:
	 * 2.反射读取所有变量,方法名
	 * 3.与状态类ParamStatus一起交给MemberHandler,MemberHandler返回拿到的reqParam,同时初始化好ParamStatus
	 * 4.与状态类MethodStatus一起交给MethodHandler,MethodHandler返回拿到的reqParam,同时初始化好MethodStatus
	 * 5.根据状态进行:   -计算Sign 和 计算重载的方法的参数         其中根据标记令哪些参数用默认值,哪些参数用传参(亦可批量去properties读取  ---若采用properties则需要syso提示一次会用到哪些参数)
	 *                  -根据 是 POST 还是 GET,调用setGET()/setPOST()       其中setGET是替换参数  setPost是将参数丢入entity
	 *                  -发起请求
	 */

	private String secretKey = "qW4nvoVVi5";
	private Class targetClass = null;
	private List<String> params = new LinkedList<>();
	private List<String> methods = new LinkedList<>();
	private ParamStatus paramStatus = new ParamStatus();
	private MethodStatus methodStatus = new MethodStatus();

	public static void main(String[] args) {
		try {
//			new APIParser().Parse("com.example.demo.api.GetByIdTEST");
//			new APIParser().Parse("com.example.demo.api.API_GetVideoList");
//			new APIParser().Parse("com.example.demo.api.API_GetById");
			new APIParser().Parse("com.example.demo.api.API_PlayTimes");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IntrospectionException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @Description: 目前测试主入口 日后需整合逻辑
	 */
	public void Parse(String _Full_Name_) throws IllegalAccessException, InstantiationException, ClassNotFoundException, IntrospectionException, NoSuchFieldException, InvocationTargetException, IOException {
		this.ParseInit( _Full_Name_ );
		this.ParseProcessing();
	}

	/**
	 * @Description: Step 2,3,4
	 */
	public void ParseInit(String _Full_Name_) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
		this.targetClass = Class.forName(_Full_Name_);
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
	public void ParseProcessing() throws NoSuchFieldException, IllegalAccessException, InstantiationException, IntrospectionException, InvocationTargetException, IOException {
		/**        -doGet还是doPost    最后处理                                                                         用不同方法,一个setEntity 一个算url
		 *           -若需要自定参数 则(读properties/cmd输入)   不需读取默认值     (从特殊方法中获取值/传入/读取)         目前假设全用默认值(只有默认值方法)
		 *            -读默认值则ifDefault   计算sign:sign的默认计算方法   NoNeed 则不执行  sign/hash 则执行对应方法    需要计算sign则计算 不需要则留为null
		 *             -根据doGet直接进行处理参数以及发起请求
		 **@CONSIDERATION: 对param的处理可以整合到ParamStatus类,同理method
		 */

		//参数键值对初始化
		Map<String, String> NVP = new LinkedHashMap<>();
		String sign = null;

		if (this.paramStatus.useDefaultValue) {
			//@CONSIDERATION:重做ParamStatus以存储值?   暂时用Map NVP代替了
			this.paramStatus.params.stream().forEach((p) -> NVP.put(p, getDefaultValue(p)));
		}else {
			//@CONSIDERATION:若不用默认值,外部获取值/传入/读取
		}

		//使用特殊计算方法
		this.methodStatus.methods.stream().forEach((name)-> {
			try {
				//加入secretKey的NVP
				Map<String,String> NVP4SK = (Map<String, String>) ((LinkedHashMap<String, String>) NVP).clone();
				NVP4SK.put("secretkey", secretKey);
				//取得对应method
				Method method = targetClass.getDeclaredMethod(name,new Class[]{Map.class});
				//设置私有可用
				method.setAccessible(true);
				//调用并获取返回,并更新
				String value = (String) method.invoke(targetClass.newInstance(),NVP4SK);
				NVP.put(name, value);
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		});


		if (!this.methodStatus.NoNeedForSign){
			//查找sign算法有没被重载
			if(this.methodStatus.methods.contains("sign")){
				//若sign被重载
			}else {
				//默认sign算法
				NVP.put("sign", getSignDefault(NVP));
			}
		}

		//判断请求类型(post/get)
		if(this.paramStatus.doGet){
			//doGet 则要做字符匹配doGetURL中的东东并替换

			//获取urlForGet
			PropertyDescriptor descriptor = new PropertyDescriptor("urlForGet", targetClass);
			Method method = descriptor.getReadMethod();
			String url = (String) method.invoke(targetClass.newInstance());
			//替换占位符
			for (String key : NVP.keySet()) {
				//处理_NotInSign后缀的参数
				if(key.contains("_NotInSign")){
					if (url.contains("{[[" + key.replace("_NotInSign", "") + "]]}")) {
						url = url.replace(("{[[" + key.replace("_NotInSign", "") + "]]}"), NVP.get(key));
					}
				}
				if (url.contains("{[[" + key + "]]}")) {
					url = url.replace(("{[[" + key + "]]}"), NVP.get(key));
				}
			}
			System.out.println("urlForGet:"+url);

			//执行doGet
			String res = this.doGet(url);
			System.out.println("Result:"+res);

		}else {
			//获取urlForPost
			PropertyDescriptor descriptor = new PropertyDescriptor("urlForPost", targetClass);
			Method method = descriptor.getReadMethod();
			String url = (String) method.invoke(targetClass.newInstance());
			//处理带_NotInSign参数
			for (String key : NVP.keySet()) {
				if(key.contains("_NotInSign")){
					if (url.contains("{[[" + key.replace("_NotInSign", "") + "]]}")) {
						url = url.replace(("{[[" + key.replace("_NotInSign", "") + "]]}"), NVP.get(key));
					}
				}
			}
			//将参数都放入做成NVP格式 方便做成entity
//			JSonObject json = new JSonObject();
//			json的格式不行
//			NVP.keySet().stream().sorted().forEach((key) -> {
//				if (!key.contains("_NotInSign")) {
//					json.put(key, NVP.get(key));
//				}
//			});
//			this.doPost(url,json);
//			System.out.println(json);
			//将参数都放入做成NVP格式 方便做成entity
			ArrayList nvps = new ArrayList();
			NVP.keySet().stream().sorted().forEach((key) -> {
				if(!key.contains("_NotInSign")) {
					nvps.add(new BasicNameValuePair(key, NVP.get(key)));
				}
			});
			String res = doPost(url,new UrlEncodedFormEntity(nvps, "utf-8"));
			System.out.println("methods:"+this.methodStatus.methods);
			System.out.println("params:"+this.paramStatus.params);
			System.out.println("nvps:"+nvps);
			System.out.println("NVP:"+NVP);
			System.out.println("urlForPost"+url);
			System.out.println("Result:"+res);

		}
	}

	/**
	 * @Description: 若需要, 按默认方式拼接sign
	 */
	private String getSignDefault(Map<String, String> NVP) {
		/**
		 * @Description:  所有参数最后加上secretkey再进行sha1
		 *                  Eg:sha1('cataid='.$cataid.'&JSONRPC='.$JSONRPC.'&writetoken='.$writetoken.$secretkey)
		 */
		StringBuilder plain = new StringBuilder();
		this.paramStatus.params.stream().sorted().forEach((p) -> getPlainValue(p, NVP, plain));
		plain.append(secretKey);
		return sha1(plain.toString());
	}

	/**
	* @Description:  获得name参数的对应默认值,给计算DefaultSign用的
	* @Param: [name, plain]
	* @return: void
	 */
	private void getPlainValue(String name, Map<String, String> NVP, StringBuilder plain) {
		//取得参数名
			String value = NVP.get(name);
			if (value.equals("") || value.equals(null) || null == value ||name.contains("_NotInSign")) {
				;
			} else if (plain.length() != 0) {
				plain.append("&" + name + "=" + value);
			} else {
				plain.append("" + name + "=" + value);
			}
	}

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

	//由于使用main来作工具,无法Autowired读项目中的HttpClient中的InfoLogger,故手动Http
	private String doGet(String url) throws IOException {
		HttpGet httpGet = new HttpGet(url);
		CloseableHttpClient client = HttpClients.createDefault();
		CloseableHttpResponse response = client.execute(httpGet);
		String res = EntityUtils.toString(response.getEntity());

		client.close();
		response.close();
		return res;
	}

	//接收url JSonObject ,处理成entity然后请求
	private void doPost(String url, JSonObject json) throws IOException {
		StringEntity entity = new StringEntity(json.toString());
		System.out.println("json:"+json);
		HttpPost httpPost = new HttpPost(url);
		httpPost.setEntity(entity);
		httpPost.setHeader("Content-Type","application/json;charset=UTF-8");
		CloseableHttpClient client = HttpClients.createDefault();
		CloseableHttpResponse response = client.execute(httpPost);
		System.out.println("request result:"+EntityUtils.toString(response.getEntity()));

		client.close();
		response.close();
	}

	//接收url NameValuePair ,处理成entity然后请求
	private String doPost(String url, HttpEntity entity) throws IOException {
		HttpPost httpPost = new HttpPost(url);
		httpPost.setEntity(entity);
		CloseableHttpClient client = HttpClients.createDefault();
		CloseableHttpResponse response = client.execute(httpPost);
		String res =EntityUtils.toString(response.getEntity());

		client.close();
		response.close();
		return res;
	}

}
