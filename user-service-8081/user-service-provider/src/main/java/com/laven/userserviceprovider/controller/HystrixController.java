package com.laven.userserviceprovider.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController()
@RequestMapping("/hystrix")
public class HystrixController {
    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(
            commandProperties = {
                    @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5"),
                    @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "5000"),
                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50")
            },
            fallbackMethod = "fallback",
            groupKey = "",
            threadPoolKey = "order-service"
    )
    @GetMapping("/order/{num}")
    public String queryOrder(@PathVariable("num") int num) {
        if (num % 2 == 0) {
            return "正常访问";
        }
        return restTemplate.getForObject("http://localhost:8082/orders", String.class);
    }

    @GetMapping("/timeout")
    public String queryOrderTimeout() {
        return restTemplate.getForObject("http://localhost:8082/orders", String.class);
    }

    public String fallback(int num) {
        return "已经熔断" + num;
    }

}
