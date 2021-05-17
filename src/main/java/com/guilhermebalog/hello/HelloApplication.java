package com.guilhermebalog.hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Arrays;

@EnableJpaRepositories("com.guilhermebalog.hello.persistence.repo")
@EntityScan("com.guilhermebalog.hello.persistence.model")
@SpringBootApplication
public class HelloApplication {
	private static ApplicationContext applicationContext;

	public static void main(String[] args) {
		applicationContext =  SpringApplication.run(HelloApplication.class, args);
		// displayAllBeans();
	}

	public static void displayAllBeans() {
		String[] allBeanNames = applicationContext.getBeanDefinitionNames();
		Arrays.sort(allBeanNames);
		for(String beanName : allBeanNames){
			System.out.println(beanName);
		}
	}

}
