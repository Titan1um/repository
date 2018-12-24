package com.example.demo.daoTests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestDBManager {

	@Value("${msg}")
	private String msg;

	@Autowired
	private Environment env;

	@Test
	public void testCoreConfig() {
		System.out.println(msg);
	}

	@Test
	public void testCoreConfig2() {
		System.out.println(env.getProperty("msg"));
	}
}
