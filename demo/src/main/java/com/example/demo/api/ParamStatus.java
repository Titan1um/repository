package com.example.demo.api;

import java.util.LinkedList;
import java.util.List;

public class ParamStatus {
	public boolean doGet;
	public boolean useDefaultValue = false;
	public List<String> params = new LinkedList<>();

	public void ParamHandler(List<String> params) {
		for (String str : params) {
			switch (str) {
				case "urlForGet":
					this.doGet = true;
					break;
				case "urlForPost":
					this.doGet = false;
					break;
				case "useDefaultValue":
					this.useDefaultValue = true;
					break;
				default:
					this.params.add(str);
					break;
			}
		}
	}
}
