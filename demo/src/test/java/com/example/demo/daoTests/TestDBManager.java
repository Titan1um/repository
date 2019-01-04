package com.example.demo.daoTests;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class TestDBManager {

	@Value("${msg}")
	private String msg;

	@Autowired
	private Environment env;

	@Test
	public void testCoreConfig() {

		Assert.assertNotNull(msg);
	}

	@Test
	public void testCoreConfig2() {

		Assert.assertNotNull(env.getProperty("msg"));
		Assert.assertEquals(env.getProperty("msg"), "test");
	}
}
