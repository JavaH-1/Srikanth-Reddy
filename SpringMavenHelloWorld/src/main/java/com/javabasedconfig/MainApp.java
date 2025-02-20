package com.javabasedconfig;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainApp {

	public static void main(String[] args) {
		//Creating Spring container
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		//Requesting Bean Object from Spring container  and storing in College reference
		College clg = context.getBean(College.class);
		clg.stdData();
	}

}
