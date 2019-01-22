package com.jun.apiparser.utils;

import com.jun.apiparser.dao.DBManager;
import com.jun.apiparser.parser.PropertiesStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: 读取File/properties/读取调用参数
 * @Author: LJH
 */
@Component
public class PropertiesMapIOUtil {

	/**
	 * @TODO: 1.读取File/properties/读取调用参数
	 * 2.分析文本(主要是生成get方式的url===========================================以及占位符类{userid}变{[[]]})
	 * 这个交给前端部分搞估计更舒服
	 * 3.写入class(默认项1~2个 getter setter)
	 * 4.重启生效
	 * 5.提供删除API功能(可能会输错/故提供删除)
	 * 6.生成源码demo
	 */
	private File file;
	/**
	 * @Todo:
	 * @Autowired
	 */
	private InfoLogger infoLogger;
	private OutputStreamWriter out;
	@Autowired
	DBManager dbManager;


	public Map<String, String> getPropertie(String propertiy_name) {
		PropertiesStatus propertiesStatus = new PropertiesStatus();
		propertiesStatus.PropertiesHandler(propertiy_name);
		Map<String, String> params = new LinkedHashMap<>();
		params.put("url", propertiesStatus.url);
		String post_get = (propertiesStatus.doGet) ? "get" : "post";
		params.put("post/get", post_get);
		params.put("secretkey", propertiesStatus.secretkey);
		if (!propertiesStatus.NoNeedForSign) {
			params.put("sign", "");
		}
		propertiesStatus.params.keySet().stream().forEach((key) -> params.put(key, propertiesStatus.params.get(key)));
		return params;
	}


	/**
	 * @Description: 扫描目录下带.properties的文件
	 * @return: java.util.List
	 * @Author: LJH
	 */
	public List getProperties() {
		List<String> myClassName = new ArrayList<String>();
		File file = new File("./");
		File[] childFiles = file.listFiles();
		for (File childFile : childFiles) {
			if (childFile.isDirectory()) {
			} else {
				String childFilePath = childFile.getPath();
				childFilePath = childFilePath.replace("\\", ".");
				childFilePath = childFilePath.replace("/", ".");
				childFilePath = childFilePath.replace("...", "");
				childFilePath = childFilePath.replace("..", "");
				if (childFilePath.contains(".properties")) {
					childFilePath = childFilePath.replace(".properties", "");
					myClassName.add(childFilePath);
				}
			}
		}
		return myClassName;
	}

	public PropertiesMapIOUtil() {
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
	 * @Description: 主入口
	 * @Param: [name, list]
	 * @return: boolean
	 * @Author: LJH
	 */
	public boolean saveProperties(String name, Map<String, String> map) {
		if (!createFile(name+".properties")) {
			infoLogger.log("failed to create file.");
		}
		for (String str : map.keySet()) {
			if (!write(str + "=" + map.get(str) + "\n")) {
				infoLogger.log("failed to write into file.");
			}
		}
		try {
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}

	public static void main(String[] args) {
		//test
		PropertiesMapIOUtil api = new PropertiesMapIOUtil();
		Map<String, String> strs = new LinkedHashMap<>();
		strs.put("Line_One", "");
		strs.put("Line_Two", null);
		strs.put("Line_Three", "Line_Three");
		api.saveProperties("helloMTFK", strs);
	}

	/**
	 * @Description: 仅创建文件
	 * @Param: [fileName]
	 * @return: boolean
	 * @Author: LJH
	 */
	public boolean createFile(String fileName) {
		file = new File(fileName);
		try {
			if (!file.exists()) {
				file.createNewFile();
			} else {
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

	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		out.flush();
		out.close();
	}

	public boolean saveDescription(String name, String description) {
		return dbManager.saveDescription(name, description);
	}

	public String  getDescription(String des){
		return dbManager.getDescription(des);
	}


}
