package com.javabased;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class BankApp {

public static void main(String[] args) {
		
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		Employee employee = context.getBean(Accountant.class);
		employee.doWork();
		context.close();
	}
}
