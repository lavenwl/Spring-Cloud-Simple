package com.laven.configserver9091;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class ConfigServer9091Application {

    public static void main(String[] args) {
        SpringApplication.run(ConfigServer9091Application.class, args);
    }

}
