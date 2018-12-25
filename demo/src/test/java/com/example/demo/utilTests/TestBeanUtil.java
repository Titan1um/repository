package com.example.demo.utilTests;

import com.example.demo.temp.FakeBeanForTest;
import com.example.demo.util.BeanUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestBeanUtil {

	@Test
	public void testGetBean() {
		FakeBeanForTest fake = (FakeBeanForTest) BeanUtil.getBean("fake");
//		FakeBeanForTest fake2 = (FakeBeanForTest) BeanUtil.getBean("fakeBeanForTest");//failed:手动命名后，原默认名不可用
		fake.test();
//		fake2.test();
	}
}
