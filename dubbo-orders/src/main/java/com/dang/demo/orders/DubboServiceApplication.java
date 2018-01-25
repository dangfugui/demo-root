package com.dang.demo.orders;

import java.io.IOException;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
public class DubboServiceApplication {

	public static void main(String[] args) throws IOException {
		// SpringApplication.run(DubboServiceApplication.class, args);
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] {"META-INF/spring/dubbo-orders.xml"});
		context.start();
		System.in.read();
		// press any key to exit
	}
}
