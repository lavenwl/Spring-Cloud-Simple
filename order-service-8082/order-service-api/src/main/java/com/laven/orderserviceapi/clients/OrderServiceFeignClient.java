package com.laven.orderserviceapi.clients;

import com.laven.orderserviceapi.OrderService;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("order-service")
public interface OrderServiceFeignClient extends OrderService {

}
