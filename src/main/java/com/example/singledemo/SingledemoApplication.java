package com.example.singledemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.example.singledemo.dao")
@SpringBootApplication
public class SingledemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SingledemoApplication.class, args);
	}
}
