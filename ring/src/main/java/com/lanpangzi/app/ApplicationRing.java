package com.lanpangzi.app;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@ComponentScan(basePackages = "com.lanpangzi")
@MapperScan(basePackages = "com.lanpangzi.mapper")
@SpringBootApplication
@EnableTransactionManagement // 开启事务管理
@EnableCaching
public class ApplicationRing {

	public static void main(String[] args) {
		SpringApplication.run(ApplicationRing.class, args);
	}

}

