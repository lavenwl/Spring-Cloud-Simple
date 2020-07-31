package com.laven.orderserviceprovider.converter;

import com.laven.dto.ItemStockDto;
import com.laven.orderserviceprovider.controller.dto.ItemDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderConverter {

    public List<ItemStockDto> itemDtos2StockDtoList(List<ItemDto> itemDtos);
}
