package com.laven.orderserviceprovider.service.impl;

import com.laven.api.R;
import com.laven.clients.IGoodsFeignClient;
import com.laven.exception.BizException;
import com.laven.orderserviceprovider.controller.dto.ItemDto;
import com.laven.orderserviceprovider.controller.dto.OrderDto;
import com.laven.orderserviceprovider.converter.OrderConverter;
import com.laven.orderserviceprovider.mapper.entitys.TbOrder;
import com.laven.orderserviceprovider.mapper.entitys.TbOrderItem;
import com.laven.orderserviceprovider.mapper.persistence.TbOrderItemMapper;
import com.laven.orderserviceprovider.mapper.persistence.TbOrderMapper;
import com.laven.orderserviceprovider.service.IOrderService;
import com.laven.vo.ItemDetailVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements IOrderService {

    @Autowired
    IGoodsFeignClient goodsFeignClient;

    @Autowired
    TbOrderMapper tbOrderMapper;

    @Autowired
    TbOrderItemMapper tbOrderItemMapper;

    @Autowired
    OrderConverter orderConverter;

    @Transactional
    @Override
    public String createOrder(OrderDto orderDto) {
        /**
         * 1. 锁定库存
         * 2. 查询商品信息
         * 3. 创建订单
         */
        R r = goodsFeignClient.decreaseStock(orderConverter.itemDtos2StockDtoList(orderDto.getItems()));
        if (r.getCode() != 200) {
            throw new BizException(r.getMsg());
        }
        List<Long> ids = orderDto.getItems().stream().map(itemDto -> Long.parseLong(itemDto.getItemId())).collect(Collectors.toList());
        R<List<ItemDetailVo>> listR = goodsFeignClient.getItemsByIds(ids);
        BigDecimal totalPrice = new BigDecimal(0);
        String orderId = UUID.randomUUID().toString().replace("-", "");
        for (ItemDto itemDto : orderDto.getItems()) {
            for (ItemDetailVo itemDetailVo : listR.getData()) {
                if (itemDetailVo.getId().toString().equals(itemDto.getItemId())) {
                    BigDecimal totalFee = itemDetailVo.getPrice().multiply(BigDecimal.valueOf(itemDto.getNum()));
                    totalPrice = totalPrice.add(totalFee);

                    TbOrderItem tbOrderItem = new TbOrderItem();
                    tbOrderItem.setItemId(itemDetailVo.getId());
                    tbOrderItem.setNum(itemDto.getNum()); // 这里为什么用detail里面的值
                    tbOrderItem.setCreateTime(new Date());
                    tbOrderItem.setOrderId(orderId);
                    tbOrderItem.setPicPath(itemDetailVo.getImage());
                    tbOrderItem.setPrice(itemDetailVo.getPrice());
                    tbOrderItem.setTotalFee(totalFee);
                    tbOrderItem.setStatus(1);
                    tbOrderItem.setTitle(itemDetailVo.getTitle());
                    tbOrderItem.setId(UUID.randomUUID().toString().replace("-", ""));
                    tbOrderItemMapper.insert(tbOrderItem);
                }
            }
        }
        TbOrder tbOrder = new TbOrder();
        tbOrder.setOrderId(orderId);
        tbOrder.setPayment(totalPrice);
        tbOrder.setStatus(0);
        tbOrder.setCreateTime(new Date());
        tbOrder.setUpdateTime(new Date());
        tbOrder.setUserId(1000000l);
        tbOrderMapper.insert(tbOrder);
        return orderId;
    }
}
