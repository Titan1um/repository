package com.example.demo.api;

import com.example.demo.util.InfoLogger;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;


public class GetByID {
	private static InfoLogger infoLogger;

	public static void main(String[] args) {
		try {
			infoLogger = new InfoLogger();
		} catch (IOException e) {
			e.printStackTrace();
		}

		String url = "http://v.polyv.net/uc/services/rest?method=getById";
		String readtoken = "66d670ea-c227-42dd-ac99-dd7dad85d23f";
		String vid = "7ca55a3c6fb1f445d9ab845be127b10b_7";
		String req = url + "&vid=" + vid + "&readtoken=" + readtoken;

		CloseableHttpClient client = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(req);
		try {
			CloseableHttpResponse response = client.execute(httpGet);
			System.out.println(EntityUtils.toString(response.getEntity()));
		} catch (Exception e) {
			e.printStackTrace();
			infoLogger.log(e.toString());
		} finally {
			try {
				client.close();
			} catch (IOException e) {
				e.printStackTrace();
				infoLogger.log(e.toString());
			}
		}
	}
}
