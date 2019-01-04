package com.example.demo.controllerTests;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class TestCallBackController {

	@Autowired
	MockMvc mvc;

	@Test
	public void test1() throws Exception {
		MvcResult mvcRes = mvc.perform(MockMvcRequestBuilders.get("/Callback"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andDo(MockMvcResultHandlers.print())
				.andReturn();
		mvcRes.getResponse().getStatus();
		Assert.assertEquals(200, mvcRes.getResponse().getStatus());


		//demo1
		mvc.perform(MockMvcRequestBuilders.get("/CallbackTest").param("realName", "zhangsan")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andDo(MockMvcResultHandlers.print())
				.andReturn();

		//demo2
		MvcResult result = mvc.perform(MockMvcRequestBuilders.get("/CallbackTest")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andDo(MockMvcResultHandlers.print())
				.andReturn();
		System.out.println(result.getResponse().toString());


	}

}
