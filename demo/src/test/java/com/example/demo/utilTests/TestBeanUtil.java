package com.example.demo.utilTests;

import com.example.demo.temp.FakeBeanForTest;
import com.example.demo.util.BeanUtil;
import com.example.demo.util.ConnectUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestBeanUtil {

	@Test
	public void testGetBean() {
		FakeBeanForTest fake = (FakeBeanForTest) BeanUtil.getBean("fakeBeanForTest");
		fake.test();
	}
}
