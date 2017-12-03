package com.hendisantika.guava.cachingdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class CachingDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CachingDemoApplication.class, args);
	}
}
