package com.jun.apiparser.view;

import java.util.List;

public class PageCreator {

	private String prefix(){
		String prefix = "<!DOCTYPE html>\n" +
				"<html lang=\"en\">\n" +
				"<head>\n" +
				"    <meta charset=\"UTF-8\">\n" +
				"    <title>Title</title>\n" +
				"</head>\n" +
				"<body>\n" +
				"<span>\n" +
				"    <a href=\"/api\">首页</a><br>\n" +
				"</span>";
		return prefix;
	}

	private String surfix(){
		String surfix = "</body>\n" +
				"</html>";
		return surfix;
	}

	private String body(List apis){
		if (apis != null) {
			return apis.toString();
		}
		else {
			return "default";
		}
	}

	private String body(String apis){
		return apis;
	}

	public String getPage(List apis){
		StringBuilder sb = new StringBuilder();
		sb.append(prefix()).append(body(apis)).append(surfix());
		return sb.toString();
	}

	public String getPage(String apis){
		StringBuilder sb = new StringBuilder();
		sb.append(prefix()).append(body(apis)).append(surfix());
		return sb.toString();
	}


}
