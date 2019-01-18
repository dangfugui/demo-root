package com.dang.demo.dubboclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.dang.demo.rpc.api.bean.Order;
import com.dang.demo.rpc.api.service.OrderService;

@SpringBootApplication()
public class DubboClientApplication {

    public static void main(String[] args) {
        // SpringApplication.run(DubboClientApplication.class, args);
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                new String[]{"META-INF/spring/*.xml"});
        context.start();
        // obtain proxy object for remote invocation
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            OrderService orderService = (OrderService) context.getBean("orderService");
            // execute remote invocation
            Order order = orderService.queryOrder(1);
        }
        // show the result
        System.out.println("time:" + (System.currentTimeMillis() - start));
    }

}
