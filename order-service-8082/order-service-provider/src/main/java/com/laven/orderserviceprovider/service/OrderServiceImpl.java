package com.laven.orderserviceprovider.service;

import com.laven.orderserviceapi.OrderService;
import com.laven.orderserviceapi.dto.OrderDto;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderServiceImpl implements OrderService {
    @Override
    public String orders() {
        return "All orders from order Service";
    }

    @Override
    public String insert( @RequestBody OrderDto dto) {
        System.out.println("接收到的对象:" + dto.toString());
        return 0 + dto.getOrderId();
    }
}
