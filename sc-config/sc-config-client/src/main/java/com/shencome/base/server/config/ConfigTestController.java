package com.shencome.base.server.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConfigTestController {

	@Value("${neo.hello}")
	private String hello;
	
	@RequestMapping("/hello")
	public String sayHello(){
		return hello;
	}
	
}
