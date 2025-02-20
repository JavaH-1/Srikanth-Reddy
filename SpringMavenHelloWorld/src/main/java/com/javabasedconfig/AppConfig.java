package com.javabasedconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.javabasedconfig.College;

@Configuration
public class AppConfig {
	
	@Bean
	public College clg() {
		College clge = new College();
		
		clge.setBranch("CSE");
		clge.setStdId(512);
		clge.setStdName("Arun");
		return clge;
	}

}
