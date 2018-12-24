package com.example.demo;

import com.example.demo.util.InfoLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
* @Description:  项目入口，目前所有exception都还没记录到InfoLogger中
* @Author: LJH
*/
@SpringBootApplication
public class DemoApplication {
	public static void main(String[] args) {
		org.springframework.boot.SpringApplication.run(DemoApplication.class, args);
	}
}

