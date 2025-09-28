package com.example.bootlearning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class BootLearningApplication {
	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(BootLearningApplication.class, args);
		var controller = context.getBean(PersonController.class);
		controller.func();

		var test1 = context.getBean(Test1.class);
		test1.print();
	}
}
