package com.laven.gateway8888;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients(basePackages = "com.laven.clients")
@SpringBootApplication
public class Gateway8888Application {

    public static void main(String[] args) {
        SpringApplication.run(Gateway8888Application.class, args);
    }

}
