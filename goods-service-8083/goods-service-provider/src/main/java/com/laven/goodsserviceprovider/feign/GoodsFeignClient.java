package com.laven.goodsserviceprovider.feign;

import com.laven.api.R;
import com.laven.clients.IGoodsFeignClient;
import com.laven.dto.ItemStockDto;
import com.laven.goodsserviceprovider.converter.ItemConverter;
import com.laven.goodsserviceprovider.mapper.entitys.TbItem;
import com.laven.goodsserviceprovider.service.IItemService;
import com.laven.vo.ItemDetailVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class GoodsFeignClient implements IGoodsFeignClient {

    @Autowired
    IItemService itemService;

    @Autowired
    ItemConverter itemConverter;

    @Override
    public R<List<ItemDetailVo>> getItemsByIds(List<Long> ids) {
        log.info("begin GoodsFeignClient.getItemsByIds: " + ids);
        List<TbItem> itemList = itemService.findItemsByIds(ids);
        List<ItemDetailVo> itemDetailVoList = itemConverter.itemDetail2VoList(itemList);
        return new R.Builder<List<ItemDetailVo>>().setData(itemDetailVoList).buildSuccess();
    }

    @Override
    public R decreaseStock(List<ItemStockDto> itemStockDtos) {
        log.info("begin GoodsFeignClient.decreaseStock: " + itemStockDtos);
        List<com.laven.goodsserviceprovider.domain.ItemStockDto> itemStockDtos1 = itemConverter.itemStockDo2DoList(itemStockDtos);
        boolean rs = itemService.decreaseStock(itemStockDtos1);
        return new R.Builder().buildSuccess();
    }
}
