package com.jun.apiparser.view;

import com.jun.apiparser.parser.PropertiesCreator;
import com.jun.apiparser.parser.PropertyParser;
import com.jun.apiparser.utils.PropertiesMapIOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
* @Description:
* @Author: LJH
*/
@Component
public class Navigator {

	@Autowired
	PropertiesMapIOUtil ioUtil;
	@Autowired
	PropertiesCreator propertiesCreator;

	public static void main(String[] args) {
		System.out.println(new Navigator().getPage());
	}

	/**
	* @Description:  当api为空时即请求首页，返回所有api
	* @return: java.lang.String
	* @Author: LJH
	*/
	public String getPage(){

		List apis = ioUtil.getProperties();
		StringBuilder sb = new StringBuilder();
		sb.append("<span>");
//		apis.stream().sorted().forEach((name)->sb.append("<label id=\""+name+"_label\">"+name+"</label> <input type=\"text\" id=\""+name+"_text\" value=\""+"value"+"\"><br>"));
		AtomicInteger count = new AtomicInteger();
		apis.stream().sorted().forEach((name)->{
			sb.append("<a href=\"/api/"+name+"\">"+name+":"+ioUtil.getDescription(name.toString())+"</a>    ");
			count.incrementAndGet();
			if((count.get()!=0)&&(((count.get() % 3) == 0))){
				sb.append("<br>");
			}
		});
		sb.append("</span>");
		return new PageCreator().getPage(sb.toString());
	}

	public String getPage(String str){
		Map<String,String> params = ioUtil.getPropertie(str);
		StringBuilder sb = new StringBuilder();
		sb.append("<form action=\"/api/"+str+"/exec\" method=\"POST\">");
		sb.append("<span>");
		sb.append("<input name=\"name\" size=\"10\" value=\""+str+"\"></label> <input type=\"text\" name=\"description\" value=\"" + ioUtil.getDescription(str) + "\"  size=\"50\"><br>");
		AtomicInteger count = new AtomicInteger(1);
		params.keySet().stream().forEach((name) -> {
			sb.append("<input name=\"" + count.get() + "_name\" size=\"10\" value=\""+name+"\"></label> <input type=\"text\" name=\"" + count.get() + "_value\" value=\"" + params.get(name) + "\"  size=\"50\"><br>");
			count.incrementAndGet();
		});
		sb.append("<input type=\"submit\" value=\"提交\"></form></span>");
		sb.append("<a href=\"/api/"+str+"/dele\"><button class='btn'>删除此方法</button><br></a>");
		return new PageCreator().getPage(sb.toString());
	}

	/**
	* @Description:  由于没有提前想好有description的存在，只好分离description的写和读
	* @Param: []
	* @return: java.util.Map
	* @Author: LJH
	*/
	private Map getDescription(){
		return null;
	}

	public String getApiExecRes(String api, HttpServletRequest req){
		//先保存
		propertiesCreator.createAPI(req);

		String res = new PropertyParser().Parse(api);
		return res;
	}
}
