package com.dang.demo.dubboclient;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

/**
 * @Date Create in 2018/1/18
 */
@Service
@Order(1)
public class DubboClient implements CommandLineRunner {

    public void run(String... args) throws Exception {

    }
}
