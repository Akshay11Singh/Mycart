package com.mycart.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan(value = { "com.mycart.controller.*", "com.mycart.service.*", "com.mycart.dto.*", "com.mycart.repository",
		"com.mycart.*" })
@EnableJpaRepositories("com.mycart.repository")
@EntityScan("com.mycart.entity")
@SpringBootApplication
public class MyCartApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyCartApplication.class, args);
	}

}
