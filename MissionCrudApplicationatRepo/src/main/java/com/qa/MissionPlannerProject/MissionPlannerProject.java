package com.qa.MissionPlannerProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class MissionPlannerProject {

	public static void main(String[] args) {
		ApplicationContext beanBag = SpringApplication.run(MissionPlannerProject.class, args);
		
		Object server = beanBag.getBean("serverStart", String.class);
		System.out.println("Server Start Time" + " " + server);
	}

}
