package com.example.demo.api;

import java.util.LinkedList;
import java.util.List;

public class MethodStatus {
	public boolean doGet;
	public boolean NoNeedForSign = false;
	public List<String> methods = new LinkedList<>();

	public void MethodHandler(List<String> methods, ParamStatus paramStatus) {
		this.doGet = paramStatus.doGet;

		for (String str : methods) {
			switch (str) {
				case "NoNeedForSign":
					this.NoNeedForSign = true;
					break;
				default:
					this.methods.add(str);
					break;
			}
		}
		trim();
		//test
		System.out.println(this.methods);
	}

	public void trim() {
		for (String str : this.methods) {
			if (str.contains("set") || str.contains("get")) {
				this.methods.remove(str);
			}
		}
	}
}
