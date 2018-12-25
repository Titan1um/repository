package com.example.demo.temp;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class FakeBeanForTest {
	public void test(){
		System.out.println("hi,mtfk");
	}
}
