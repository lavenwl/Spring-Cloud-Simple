package com.laven.goodsserviceprovider.converter;

import com.laven.goodsserviceprovider.domain.ItemStockDto;
import com.laven.goodsserviceprovider.mapper.entitys.TbItem;
import com.laven.vo.ItemDetailVo;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ItemConverter {

    public List<ItemDetailVo> itemDetail2VoList(List<TbItem> itemList);

    List<ItemStockDto> itemStockDo2DoList(List<com.laven.dto.ItemStockDto> itemStockDtos);
}
