package com.csit.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration(exclude = { 
				org.activiti.spring.boot.RestApiAutoConfiguration.class,
				org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration.class,
				org.activiti.spring.boot.SecurityAutoConfiguration.class, })
public class SimpleActivitiBootWithHibernateApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimpleActivitiBootWithHibernateApplication.class, args);
	}
}
