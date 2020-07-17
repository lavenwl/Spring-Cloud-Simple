package com.laven.orderserviceapi;

import com.laven.orderserviceapi.dto.OrderDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

public interface OrderService {

    @GetMapping("/orders")
    String orders();

    @PostMapping("/order")
    String insert(OrderDto dto);
}
