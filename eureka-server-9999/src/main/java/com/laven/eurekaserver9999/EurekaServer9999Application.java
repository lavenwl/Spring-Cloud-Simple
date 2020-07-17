package com.laven.eurekaserver9999;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class EurekaServer9999Application {

    public static void main(String[] args) {
        SpringApplication.run(EurekaServer9999Application.class, args);
    }

}
