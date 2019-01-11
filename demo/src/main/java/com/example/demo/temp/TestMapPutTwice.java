package com.example.demo.temp;


import java.util.LinkedHashMap;
import java.util.Map;

public class TestMapPutTwice {
	public static void main(String[] args) {
		Map<String, String> map = new LinkedHashMap<>();
		map.put("hi", "hi");
		map.put("test", "test");
		System.out.println(map);
		map.put("test", "shit");
		System.out.println(map);
	}
}
