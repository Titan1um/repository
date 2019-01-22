package com.jun.apiparser.parser;

import com.jun.apiparser.utils.InfoLogger;
import com.jun.apiparser.utils.PropertiesMapIOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.*;

/**
 * @Description: 读取文本/properties创建类
 * @Author: LJH
 */
@Component
public class PropertiesCreator {

	@Autowired
	PropertiesMapIOUtil ioUtil;

	/**
	* @Description: 入口函数 接受req并区别处理最后交付一个Map给create()
	 *                  总步骤：接受所有参数  过滤参数  计算出url 返回map并保存
	* @Param: [req]
	* @return: boolean
	* @Author: LJH
	*/
	public boolean createAPI(HttpServletRequest req){

		//TODO：先全收入map  再过滤  最后拼接url

		Map<String,String> params_Origin = new LinkedHashMap<>();
		MapNotNullSave("name", req.getParameter("name"), params_Origin);
		MapNotNullSave("description", req.getParameter("description"), params_Origin);
		String res = "";
		for(int i = 1;res!=null;i++){
			res = req.getParameter(i + "_name");
			if(req.getParameter(i+"_sign") != null && req.getParameter(i+"_sign").equals("0")){
				MapNotNullSave(req.getParameter(i + "_name")+"_NotInSign", req.getParameter(i + "_value"), params_Origin);
			}else {
				MapNotNullSave(req.getParameter(i + "_name"), req.getParameter(i + "_value"), params_Origin);
			}
		}
		//Todo: check it and del
		System.out.println(params_Origin);


		Map<String,String> params = new LinkedHashMap<>();
		String fileName = "";
		String description;
		//过滤
		for(String key : params_Origin.keySet()){
			switch (key) {
				case "name":
					fileName = params_Origin.get(key);
					break;
				case "description":
					description = params_Origin.get(key);
					break;
				case "post/get":
					if(params_Origin.get(key).toUpperCase().equals("get".toUpperCase())){
						MapNotNullSave("urlForGet", params_Origin.get("url"),params);
					}else if(params_Origin.get(key).toUpperCase().equals("post".toUpperCase())){
						MapNotNullSave("urlForPost", params_Origin.get("url"),params);
					}
					break;
				case "url":
					break;
/** @TODO:注意sign要不要过滤掉，过不过其实都行  现在不过滤，则改掉拼接那里默认加sign的操作
				case "sign":
					break;
 */
				default:
					MapNotNullSave(key, params_Origin.get(key), params);
					break;
			}
		}
		//Todo: check it and del
		System.out.println(params);

		//拼接
		if(params.containsKey("urlForGet")){
			StringBuilder url = new StringBuilder();
			url.append(params.get("urlForGet"));
			url.append("?");
			Set<String> strs = new HashSet<>();
			params.keySet().stream().forEach((str)->strs.add(str));
			strs.stream().forEach((key)->{
				if(!key.equals("urlForGet")&&!key.equals("secretkey")&&!key.contains("_NotInSign")){
					if(!params.get("urlForGet").contains("{"+key+"}")) {
						url.append(key+"={"+key+"}&");
					}else{
						params.put(key+"_NotInSign", params.get(key));
						params.remove(key);
					}
				}
			});//记得去掉最后的&
			/**
			 * Todo:不需要默认补齐sign 有则在url中，但不放入properties
			 * url.append("sign={sign}")
			 */

			String result = url.toString();
			result = result.substring(0, result.length()-1);

			MapNotNullSave("urlForGet", result, params);
		}
		//Todo: check it and del
		System.out.println("Params finished:"+params);


		//写入
		params.remove("sign");
		if (!ioUtil.saveProperties(fileName, params)){
			return false;
		}
		//@Todo：此处应插入description
		if(!ioUtil.saveDescription(fileName, params_Origin.get("description"))){
			return false;
		}
		return true;
	}

	private void MapNotNullSave(String name,String value,Map map){
		if(name != null) {
			if(value == null) {
				map.put(name, "");
			}else if(name.equals("")){
				;
			}
			else {
				map.put(name, value);
			}
		}
	}

}
