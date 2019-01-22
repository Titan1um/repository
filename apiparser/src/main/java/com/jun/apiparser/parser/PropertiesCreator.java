package com.jun.apiparser.parser;

import com.jun.apiparser.utils.InfoLogger;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @Description: 读取文本/properties创建类
 * @Author: LJH
 */
public class PropertiesCreator {
	/**
	 * @TODO: 1.读取File/properties/读取调用参数
			 * 2.分析文本(主要是生成get方式的url===========================================以及占位符类{userid}变{[[]]})
	         *                                                   这个交给前端部分搞估计更舒服
			 * 3.写入class(默认项1~2个 getter setter)
			 * 4.重启生效
			 * 5.提供删除API功能(可能会输错/故提供删除)
			 * 6.生成源码demo
	 */
	private File file;
	/**
	 * @Todo:
	 *          @Autowired
	 */
	private InfoLogger infoLogger;
	private OutputStreamWriter out;

	public PropertiesCreator() {
		try {
			infoLogger = new InfoLogger();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public boolean write(String str) {
		try {
			out.write(str);
			out.flush();
		} catch (IOException e) {
			infoLogger.log(e.toString());
			return false;
		}
		return true;
	}

	/**
	* @Description:  主入口
	* @Param: [name, list]
	* @return: boolean
	* @Author: LJH
	*/
	public boolean create(String name, Map<String,String> map) {
		if (!createFile(name)){
			infoLogger.log("failed to create file.");
		}
		for(String str : map.keySet()){
			if (!write(str+"="+map.get(str)+"\n")){
				infoLogger.log("failed to write into file.");
			}
		}
		return true;
	}

	public static void main(String[] args) {
		//test
		PropertiesCreator api = new PropertiesCreator();
		Map<String,String> strs = new LinkedHashMap<>();
		strs.put("Line_One", "");
		strs.put("Line_Two", null);
		strs.put("Line_Three", "Line_Three");
		api.create("helloMTFK", strs);
	}

	/**
	* @Description:  仅创建文件
	* @Param: [fileName]
	* @return: boolean
	* @Author: LJH
	*/
	public boolean createFile(String fileName) {
		file = new File(fileName + ".properties");
		try {
			if (!file.exists()) {
				file.createNewFile();
			}else {
				infoLogger.log("File name conflict!");
			}
			out = new OutputStreamWriter(new FileOutputStream(file));
		} catch (Exception e) {
			infoLogger.log(e.toString());
			return false;
		}
		file.setWritable(true);
		return true;
	}


	/**
	* @Description: 入口函数 接受req并区别处理最后交付一个Map给create()
	* @Param: [req]
	* @return: boolean
	* @Author: LJH
	*/
	public boolean createAPI(HttpServletRequest req){

		//TODO：先全收入map  再过滤  最后拼接url

		Map<String,String> params_Origin = new LinkedHashMap<>();
		MapNotNullSave(req.getParameter("name"), req.getParameter("description"), params_Origin);
		String res = "";
		for(int i = 1;res!=null;i++){
			res = req.getParameter(i + "_name");
			if(req.getParameter(i+"_sign") != null && req.getParameter(i+"_sign").equals("0")){
				MapNotNullSave(req.getParameter(i + "_name")+"_NotInSign", req.getParameter(i + "_value"), params_Origin);
			}else {
				MapNotNullSave(req.getParameter(i + "_name"), req.getParameter(i + "_value"), params_Origin);
			}
		}


		Map<String,String> params = new LinkedHashMap<>();
		String fileName = "";
		String description;
		//过滤
		for(String key : params_Origin.keySet()){
			switch (key) {
				case "name":
					fileName = key;
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
				default:
					MapNotNullSave(key, params_Origin.get(key), params);
					break;
			}
		}
		//拼接
		if(params.containsKey("urlForGet")){
			StringBuilder url = new StringBuilder();
			url.append(params.get("urlForGet"));
			url.append("?");
			params.keySet().stream().forEach((key)->{
				if(!key.equals("urlForGet")&&!key.equals("secretkey")&&!key.contains("_NotInSign")){
					url.append(key+"={"+key+"}&");
				}
			});//&
			url.append("sign={sign}");//TODO:未规定格式
			MapNotNullSave("urlForGet", url.toString(), params);
		}
		System.out.println("Params finished:"+params);


		//写入
		if (!create(fileName, params)){
			return false;
		}
		//@Todo：此处应插入description
		return true;
	}

	private void MapNotNullSave(String name,String value,Map map){
		if(name != null) {
			if(value == null) {
				map.put(name, "");
			}
			else {
				map.put(name, value);
			}
		}
	}

	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		out.flush();
		out.close();
	}


}
