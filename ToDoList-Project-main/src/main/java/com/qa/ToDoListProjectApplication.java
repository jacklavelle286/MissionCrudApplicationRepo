package com.qa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class ToDoListProjectApplication {

	public static void main(String[] args) {
		ApplicationContext beanBag = SpringApplication.run(ToDoListProjectApplication.class, args);
		
		Object server = beanBag.getBean("serverStart", String.class);
		System.out.println("Server Start Time" + " " + server);
	}

}
