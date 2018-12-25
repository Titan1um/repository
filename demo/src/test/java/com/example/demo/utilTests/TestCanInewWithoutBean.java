package com.example.demo.utilTests;

import com.example.demo.util.ConnectUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestCanInewWithoutBean {

	ConnectUtil util;

	@Test
	public void testCanInewWithBean(){
		util = new ConnectUtil();
		try {
			util.getInstance();
		} catch (Exception e) {
			;
		}
		//the answer is:no run as bean,autowired lost!
	}
}
