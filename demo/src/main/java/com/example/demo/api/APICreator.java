package com.example.demo.api;

import com.example.demo.util.InfoLogger;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
* @Description:  读取文本/properties创建类
* @Author: LJH
*/
public class APICreator {
	/**@// TODO: 1.读取File/properties/读取调用参数
	 *           2.分析文本
	 *           3.写入class(默认项1~2个 getter setter)
	 *           4.重启生效
	 *           5.提供删除API功能(可能会输错/故提供删除)
	 *           6.生成源码demo
	 */
	private File file;
	//	@Autowired
	private InfoLogger infoLogger;

	public APICreator(){
		try {
			infoLogger = new InfoLogger();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public boolean createFile(String fileName){
		file = new File(fileName+".properties");
		try {
			if (!file.exists()){
				file.createNewFile();
			}
		}catch (Exception e){
			infoLogger.log(e.toString());
		}
		return true;
	}

	public boolean write(){
		return true;
	}

	public boolean create(String name,List<String> list){
		return true;
	}

	public static void main(String[] args){
		new APICreator().createFile("helloMTFK");
	}
}
