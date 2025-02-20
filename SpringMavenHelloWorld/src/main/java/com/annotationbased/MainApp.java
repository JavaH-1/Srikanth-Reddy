package com.annotationbased;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.annotationbased.Sport;

public class MainApp {
	public static void main(String[] args)
	{
        //Spring container is created
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		
        //Bean object is requested from Spring Container and stored in Student reference
		Sport sp = context.getBean(Sport.class);

		sp.display();
	}
}
