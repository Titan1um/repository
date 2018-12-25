package com.example.demo.tempTests;

import com.example.demo.temp.TestProperties;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class testProperties {

	@Autowired
	TestProperties testProperties;

	@Test
	public void test(){
		testProperties.testConfig();
	}
}
