package com.example.demo.util;

import java.io.PrintStream;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class HttpClientUtil
{
  private static HttpClientUtil httpClient = null;






  public static HttpClientUtil getInstance()
  {
    if (null == httpClient) {
      httpClient = new HttpClientUtil();
      return httpClient;
    }
    return httpClient;
  }







  private HttpPost setPost(String url, HttpEntity entity)
  {
    HttpPost post = new HttpPost(url);
    post.setEntity(entity);
    return post;
  }





  private HttpPost setPost(String url, JSONObject jsonObject)
    throws Exception
  {
    HttpPost post = new HttpPost(url);
    StringEntity entity = new StringEntity(jsonObject.toString());
    post.setEntity(entity);
    return post;
  }







  private String doPost(HttpPost httpPost)
  {
    CloseableHttpClient httpClient = null;
    CloseableHttpResponse response = null;
    String token = null;

    try
    {
      httpClient = HttpClients.createDefault();


      response = httpClient.execute(httpPost);
      System.out.println("Http请求状态码：{}" + response.getStatusLine().getStatusCode());

      System.out.println("httppost:" + EntityUtils.toString(httpPost.getEntity()) + "\n==============================================");












      token = getReponseContent(response);




      try
      {
        response.close();
        httpClient.close();
      }
      catch (Exception e) {
        e.printStackTrace();
      }

      System.out.println("=================>" + token + "<=================");
    }
    catch (Exception e)
    {
      e.printStackTrace();
    } finally {
      try {
        response.close();
        httpClient.close();
      }
      catch (Exception e) {
        e.printStackTrace();
      }
    }


    return token;
  }







  public HttpGet setGet(String url)
  {
    HttpGet httpGet = new HttpGet(url);
    return httpGet;
  }





  public String doGet(HttpGet httpGet)
  {
    CloseableHttpClient client = null;
    CloseableHttpResponse response = null;
    try {
      client = HttpClients.createDefault();
      response = client.execute(httpGet);
      return EntityUtils.toString(response.getEntity());
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      try {
        response.close();
        client.close();
      }
      catch (Exception e) {
        e.printStackTrace();
      }
    }
		return null;
  }








  public String getVideoList(String url, String sign)
  {
    String req = url + "&sign=" + sign;
    HttpGet httpGet = new HttpGet(req);
    return doGet(httpGet);
  }








  public String getToken(String url, HttpEntity entity)
  {
    return doPost(setPost(url, entity));
  }







  public String getToken(String url, JSONObject json)
  {
    StringEntity entity = new StringEntity(json.toString(), "utf-8");
    return doPost(setPost(url, entity));
  }





  public String getToken(String url, List list)
    throws java.io.UnsupportedEncodingException
  {
    HttpEntity entity = null;
    entity = new UrlEncodedFormEntity(list, org.apache.commons.codec.Charsets.UTF_8);
    return doPost(setPost(url, entity));
  }








  private String getReponseContent(CloseableHttpResponse response)
    throws Exception
  {
    String readContent = null;
    HttpEntity entity = response.getEntity();
    readContent = EntityUtils.toString(entity);
    System.out.println(readContent);
    return readContent;
  }
}


