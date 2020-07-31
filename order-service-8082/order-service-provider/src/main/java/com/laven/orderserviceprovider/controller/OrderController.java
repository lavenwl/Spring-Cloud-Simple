package com.laven.orderserviceprovider.controller;

import com.laven.api.R;
import com.laven.orderserviceprovider.controller.dto.OrderDto;
import com.laven.orderserviceprovider.service.IOrderService;
import org.apache.ibatis.binding.BindingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class OrderController {

    @Autowired
    IOrderService orderService;

    @PostMapping("/order")
    public R order(@RequestBody @Validated OrderDto orderDto, BindingResult bindingResult) {
        orderDto.validdData(bindingResult);
        String orderId = orderService.createOrder(orderDto);
        return new R.Builder().setData(orderId).buildSuccess();
    }

}
