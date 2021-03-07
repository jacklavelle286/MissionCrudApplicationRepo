package com.qa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(scanBasePackages={"com.qa.services.MissionsService", "com.qa.rest.ToDoController"})
public class MyStarterProjectCrudApplication {
 

	private static final String[] String = null;

	@Bean("entityManagerFactory")
	public static void main(String[] args) {
		ApplicationContext beanBag = SpringApplication.run(MyStarterProjectCrudApplication.class, args);
		
		//Object server = beanBag.getBean("serverStart", String.class);
		//System.out.println("Server Start Time" + " " + server);
	}

}
