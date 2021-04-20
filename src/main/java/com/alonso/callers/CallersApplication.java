package com.alonso.callers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@PropertySource("classpath:application.properties")
@EnableJpaRepositories(basePackages = "com.alonso.callers.repository")
@EnableTransactionManagement
public class CallersApplication {

	public static void main(String[] args) {
		SpringApplication.run(CallersApplication.class, args);
	}

}
