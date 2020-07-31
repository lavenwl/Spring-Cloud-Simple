package com.laven.goodsserviceprovider.service;

import com.laven.goodsserviceprovider.domain.ItemStockDto;
import com.laven.goodsserviceprovider.mapper.entitys.TbItem;

import java.util.List;

public interface IItemService {

    List<TbItem> findItemsByIds(List<Long> ids);

    boolean decreaseStock(List<ItemStockDto> itemStockDtos);
}
