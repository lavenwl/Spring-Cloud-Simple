package com.laven.userserviceprovider.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope // 可以动态的更新读取到的配置信息的内容
@RestController
public class  ConfigController {
    @Value("${env}")
    private String env;

//    @Autowired
//    OrderServiceFeignClient orderService;
//
//    @GetMapping("/config")
//    public String config() {
//        return "当前环境:" + env;
//    }
//
//    @GetMapping("/orders")
//    public String orders() {
//        return orderService.orders();
//    }
//
//    @PostMapping("/order")
//    public String order() {
//        OrderDto orderDto = new OrderDto();
//        orderDto.setOrderId("OrderDtoID");
//        System.out.println("发送的dto:" + orderDto.toString());
//        return orderService.insert(orderDto);
//    }
}
