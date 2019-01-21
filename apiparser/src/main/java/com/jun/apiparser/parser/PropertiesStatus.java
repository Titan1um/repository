package com.jun.apiparser.parser;

import java.io.*;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class PropertiesStatus {
	public boolean doGet;
	public boolean useDefaultValue = false;
	public Map params = new LinkedHashMap();
	public boolean NoNeedForSign = false;
	List<String> properties = new LinkedList<>();
	private BufferedReader in;

	/**
	 * @Description: 读properties无法用特殊处理方法, 故那些api只能交付给apiParser处理
	 */
	//public List<String> methods = new LinkedList<>();

	public boolean PropertiesHandler(String fileName) {
		if(!getProperties(fileName)){
			return false;
		}
		//出现IO错误则log并返回false
		return true;
	}

	private boolean getProperties(String fileName){
		try {
			in = new BufferedReader(new InputStreamReader(new FileInputStream(new File(fileName))));
			String tmp;
			do{
				tmp = in.readLine();
				if(tmp!=null){
					properties.add(tmp);
				}
			}while (tmp!=null);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}

	public void ParamHandler(String fileName) {
		if(!getProperties(fileName)){
			System.out.println("Failed to open file and get properties.");
		}
		
	}

	public void MethodHandler(List<String> methods, ParamStatus paramStatus) {
		this.doGet = paramStatus.doGet;

		for (String str : methods) {
			switch (str) {
				case "NoNeedForSign":
					this.NoNeedForSign = true;
					break;
				default:
					break;
			}
		}
		trim();
	}

	public void trim() {
		List<String> tmpStr = new LinkedList<>();
		for (String str : this.methods) {
			if (!str.contains("set") && !str.contains("get") && !str.contains("is")) {
				tmpStr.add(str);
			}
		}
		this.methods = tmpStr;
	}
}
