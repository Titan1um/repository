package com.example.demo.temp;

import java.io.IOException;
import java.io.OutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;

public class TmpForHorseLightReq {
	public static void tmp(HttpServletResponse resp, String json) throws IOException {
		OutputStream os = resp.getOutputStream();
		StringEntity entity = new StringEntity(json);
		os.write(EntityUtils.toByteArray(entity));
	}
}
