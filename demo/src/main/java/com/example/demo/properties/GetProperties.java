package com.example.demo.properties;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author lenovo
 */
@Component
public class GetProperties {

	@Resource
	Environment env;

	public String[] getProperty(){
		System.out.println(env.getProperty("msg"));
		System.out.println(env.getProperty("exist"));
		return null;
	}

	public Environment getEnv(){
		return env;
	}
}
