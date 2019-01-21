package com.jun.apiparser.view;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
* @Description:
* @Author: LJH
*/
public class Navigator {


	public static void main(String[] args) {
//		//test for package scan
//		String packageName = "com.jun.apiparser.api";
//
//		List<String> classNames = getClassName(packageName);
//		for (String className : classNames) {
//			System.out.println(className);
//		}

		new Navigator().getProperties();
	}

	/**
	* @Description:  当api为空时即请求首页，返回所有api
	* @return: java.lang.String
	* @Author: LJH
	*/
	public String getPage(){

		List apis = getProperties();
		StringBuilder sb = new StringBuilder();
		sb.append("<span>");
//		apis.stream().sorted().forEach((name)->sb.append("<label id=\""+name+"_label\">"+name+"</label> <input type=\"text\" id=\""+name+"_text\" value=\""+"value"+"\"><br>"));
		AtomicInteger count = new AtomicInteger();
		apis.stream().sorted().forEach((name)->{
			sb.append("<a href=\"/api/"+name+"\">"+name+"</a>    ");
			if((count.get()!=0)&&(count.getAndIncrement()%5 == 0)){
				sb.append("<br>");
			}
		});
		sb.append("</span>");
		return new PageCreator().getPage(sb.toString());
	}

	public String getPage(String str){
		List list = new LinkedList();
		list.add(str);
		return new PageCreator().getPage(list);
	}

	private List getProperties(){
		List<String> myClassName = new ArrayList<String>();
		File file = new File("./");
		File[] childFiles = file.listFiles();
		for (File childFile : childFiles) {
			if (childFile.isDirectory()) {
			} else {
				String childFilePath = childFile.getPath();
				childFilePath = childFilePath.replace("\\", ".");
				childFilePath = childFilePath.replace("...", "");
				childFilePath = childFilePath.replace("..", "");
				if(childFilePath.contains(".properties")) {
					childFilePath = childFilePath.replace(".properties", "");
					myClassName.add(childFilePath);
				}
			}
		}
		return myClassName;
	}
}
