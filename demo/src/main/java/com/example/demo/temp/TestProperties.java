package com.example.demo.temp;

import com.example.demo.properties.GetProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class TestProperties {

	@Autowired
	GetProperties getProperties;

	public void testConfig(){
		//从context获取properties
		getProperties.getProperty();

		Environment env = getProperties.getEnv();
		System.out.println(env.getProperty("exist"));
	}
}
