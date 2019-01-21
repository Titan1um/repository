package com.jun.apiparser.parser;

import com.jun.apiparser.utils.InfoLogger;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.List;

/**
 * @Description: 读取文本/properties创建类
 * @Author: LJH
 */
public class PropertiesCreator {
	/**
	 * @TODO: 1.读取File/properties/读取调用参数
			 * 2.分析文本
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

	public boolean create(String name, List<String> list) {
		if (!createFile(name)){
			infoLogger.log("failed to create file.");
		}
		for(String str : list){
			if (!write(str+"\n")){
				infoLogger.log("failed to write into file.");
			}
		}
		return true;
	}

	public static void main(String[] args) {
		//test
		PropertiesCreator api = new PropertiesCreator();
		List<String> strs = new LinkedList<>();
		strs.add("Line_One");
		strs.add("Line_Two");
		strs.add("Line_Three");
		api.create("helloMTFK", strs);
	}

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


	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		out.flush();
		out.close();
	}
}
