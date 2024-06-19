package com.springboot.userinfo;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableBatchProcessing
@ComponentScan({"com.springboot.userinfo.config","com.springboot.userinfo.service","com.springboot.userinfo.listener",
		"com.springboot.userinfo.reader","com.springboot.userinfo.writer","com.springboot.userinfo.processor"})
public class UserinfoApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserinfoApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
