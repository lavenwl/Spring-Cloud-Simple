package com.laven.orderserviceprovider.service;

import com.laven.orderserviceprovider.controller.dto.OrderDto;

public interface IOrderService {
    String createOrder(OrderDto orderDto);
}
