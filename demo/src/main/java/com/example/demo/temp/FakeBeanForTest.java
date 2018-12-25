package com.example.demo.temp;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component("fake")
public class FakeBeanForTest {
	public String test(){
		System.out.println("hi,mtfk");
		return "hi,mtfk";
	}
}
