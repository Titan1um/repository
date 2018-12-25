package com.example.demo.tempTests;

import com.example.demo.temp.FakeBeanForTest;
import com.example.demo.util.BeanUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestTest {

	@Test
	public void testAssert(){

		FakeBeanForTest fake = (FakeBeanForTest) BeanUtil.getBean("fake");
		Assert.assertNotNull("null", fake);
		Assert.assertTrue("False", null != fake.test() && fake.test().equals("hi,mtfk"));
	}
}