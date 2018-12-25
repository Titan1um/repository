package com.example.demo.properties;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:params.properties")
public class PropertiesConfig {
}
