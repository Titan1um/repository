package com.jun.apiparser.parser;

import java.io.*;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class PropertiesStatus {
	/**
	 * @Description: TODO：事实上在propertiesParser中，useDefault和NoNeedForSign几乎无用，应在文本中省去
	 */
	public boolean doGet;
	public boolean useDefaultValue = false;
	public Map<String,String> params = new LinkedHashMap<String,String>();
	public boolean NoNeedForSign = false;
	public String url;
	public String secretkey="qW4nvoVVi5";
	List<String> properties = new LinkedList<>();
	private BufferedReader in;

	/**
	 * @Description: 读properties无法用特殊处理方法, 故那些api只能交付给apiParser处理
	 */
	//public List<String> methods = new LinkedList<>();

	public boolean PropertiesHandler(String fileName) {
		if(!getProperties(fileName)){
			System.out.println("Failed to open file and get properties.");
			return  false;
		}
		this.useDefaultValue = true;
		//分割“=” 判断名称 区别处理 放入map
		for(String str : properties){
			splitAndPut(str);
		}

		properties.clear();
		for(String key : params.keySet()){
			properties.add(key);
		}

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

	public void splitAndPut(String str){
		String[] strs = str.split("=");
		//判断[0]  拼接[1],[2]....
		String param = strs[0];
		if(param.contains("_NotInSign")){
			param = param.replace("_NotInSign", "");
		}
		switch (param) {
			case "NoNeedForSign":
				this.NoNeedForSign = true;
				break;
			case "useDefaultValue":
				this.useDefaultValue = true;
				break;
			case  "urlForPost":
				this.doGet = false;
				this.url = contact(strs);
				break;
			case "urlForGet":
				this.doGet = true;
				this.url = contact(strs);
				break;
			case "ptime":
				this.params.put("ptime", String.valueOf(System.currentTimeMillis()));
				break;
			case "secretkey":
				this.secretkey= contact(strs);
				System.out.println("==========================="+secretkey);
				break;
			case "description":
				break;
			default:
				this.params.put(param, contact(strs));
				break;
		}
	}

	public String contact(String[] strs){
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i < strs.length; i++) {
			sb.append("="+strs[i]);
		}
		String res = sb.toString();
		return res.substring(1, res.length());
	}

}
