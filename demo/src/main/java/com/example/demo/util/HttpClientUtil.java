package com.example.demo.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: HttpClient类包装以供使用
 *               直接使用doPost doGet getToken即可
 *               InfoLogger 若不使用则删除声明和在异常中的使用
 * @Author: LJH
 */
public class HttpClientUtil {
	/**
	* @Description: InfoLogger 若不使用则删除声明和在异常中的使用
	* @Param:
	* @return:
	* @Author: LJH
	*/
	@Autowired
	private InfoLogger infoLogger;

	private static HttpClientUtil httpClient = null;


	public static HttpClientUtil getInstance() {
		if (null == httpClient) {
			httpClient = new HttpClientUtil();
			return httpClient;
		}
		return httpClient;
	}


	private HttpPost setPost(String url, HttpEntity entity) {
		HttpPost post = new HttpPost(url);
		post.setEntity(entity);
		return post;
	}


	public HttpPost setPost(String url, JSONObject jsonObject) {
		HttpPost post = new HttpPost(url);
		StringEntity entity = null;
		try {
			entity = new StringEntity(jsonObject.toString());
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			infoLogger.log(e.toString());
		}
		post.setEntity(entity);
		return post;
	}


	public String doPost(HttpPost httpPost) {
		CloseableHttpClient httpClient = null;
		CloseableHttpResponse response = null;
		String token = null;

		try {
			httpClient = HttpClients.createDefault();

			response = httpClient.execute(httpPost);

			token = getReponseContent(response);
		} catch (Exception e) {
			e.printStackTrace();
			infoLogger.log(e.toString());
		} finally {
			try {
				if (null != response) {
					response.close();
				}
				if (null != httpClient) {
					httpClient.close();
				}
			} catch (Exception e) {
				infoLogger.log(e.toString());
				e.printStackTrace();
			}
		}

		return token;
	}

	private HttpGet setGet(String url) {
		HttpGet httpGet = new HttpGet(url);
		return httpGet;
	}


	public String doGet(HttpGet httpGet) {
		CloseableHttpClient client = null;
		CloseableHttpResponse response = null;
		try {
			client = HttpClients.createDefault();
			response = client.execute(httpGet);
			return EntityUtils.toString(response.getEntity());
		} catch (Exception e) {
			e.printStackTrace();
			infoLogger.log(e.toString());
		} finally {
			try {
				if (null != response) {
					response.close();
				}
				if (null != client) {
					client.close();
				}
			} catch (Exception e) {
				infoLogger.log(e.toString());
				e.printStackTrace();
			}
		}
		return null;
	}


	public String getVideoList(String url, String sign) {
		String req = url + "&sign=" + sign;
		HttpGet httpGet = new HttpGet(req);
		return doGet(httpGet);
	}


	/**
	 * @Description: post方式将entity发送到指定url
	 * @Param: [url, entity]
	 * @return: java.lang.String
	 * @Author: LJH
	 */
	public String getToken(String url, HttpEntity entity) {
		return doPost(setPost(url, entity));
	}


	public String getToken(String url, JSONObject json) {
		StringEntity entity = new StringEntity(json.toString(), "utf-8");
		return doPost(setPost(url, entity));
	}

	public String getToken(String url, JSonObject json) {
		StringEntity entity = new StringEntity(json.toString(), "utf-8");
		return doPost(setPost(url, entity));
	}


	public String getToken(String url, List list) {
		HttpEntity entity = null;
		entity = new UrlEncodedFormEntity(list, org.apache.commons.codec.Charsets.UTF_8);
		return doPost(setPost(url, entity));
	}


	private String getReponseContent(CloseableHttpResponse response) {
		String readContent = null;
		HttpEntity entity = response.getEntity();
		try {
			readContent = EntityUtils.toString(entity);
		} catch (IOException e) {
			e.printStackTrace();
			infoLogger.log(e.toString());
		}
		System.out.println(readContent);
		return readContent;
	}
}


