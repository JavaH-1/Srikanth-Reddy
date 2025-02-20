package com.studytonight.example;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Hello {
	
	public static void main(String[] args) {
 
		// loading the Bean and XML definitions from the given XML file
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
//		HelloWorldService obj = context.getBean(HelloWorldService.class);
//		Employee empl = (Employee) context.getBean("emp");
		EmployeeInterface ei = (EmployeeInterface) context.getBean("helloWorldService");
		ei.empData();
//		obj.hello();
//		empl.empData();
		context.close();
	}
}