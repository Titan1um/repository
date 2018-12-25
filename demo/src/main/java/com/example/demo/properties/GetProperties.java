package com.example.demo.properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
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
		return null;
	}
}
