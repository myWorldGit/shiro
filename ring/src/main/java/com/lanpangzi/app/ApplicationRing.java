package com.lanpangzi.app;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@ComponentScan(basePackages = "com.lanpangzi")
@MapperScan(basePackages = "com.lanpangzi.mapper")
@SpringBootApplication
public class ApplicationRing {

	public static void main(String[] args) {
		SpringApplication.run(ApplicationRing.class, args);
	}

}

