package com.dang.demo.kafka;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KafakaServiceApplication {

	public static void main(String[] args) throws IOException {
		 SpringApplication.run(KafakaServiceApplication.class, args);
	}
}