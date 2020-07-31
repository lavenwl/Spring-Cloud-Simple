package com.laven.goodsserviceprovider.service.impl;

import com.laven.exception.BizException;
import com.laven.goodsserviceprovider.domain.ItemStockDto;
import com.laven.goodsserviceprovider.mapper.entitys.TbItem;
import com.laven.goodsserviceprovider.mapper.entitys.TbItemExample;
import com.laven.goodsserviceprovider.mapper.persistence.TbItemMapper;
import com.laven.goodsserviceprovider.service.IItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ItemServiceImpl implements IItemService {

    @Autowired
    TbItemMapper tbItemMapper;

    @Override
    public List<TbItem> findItemsByIds(List<Long> ids) {
        TbItemExample tbItemExample = new TbItemExample();
        tbItemExample.createCriteria().andIdIn(ids);
        return tbItemMapper.selectByExample(tbItemExample);
    }

    @Transactional
    @Override
    public boolean decreaseStock(List<ItemStockDto> itemStockDtos) {
        List<Long> ids = itemStockDtos.stream().map(ItemStockDto::getItemId).collect(Collectors.toList());
        List<TbItem> tbItemList = tbItemMapper.selectStockForUpdate(ids);
        if (tbItemList == null || tbItemList.isEmpty()) {
            throw new BizException("未找到对应商品");
        }
        if (ids.size() != tbItemList.size()) {
            throw new BizException("部分商品不存在");
        }
        itemStockDtos.forEach(itemStockDto -> {
            tbItemList.forEach(tbItem -> {
                if (Objects.equals(tbItem.getId(), itemStockDto.getItemId())) {
                    if (tbItem.getNum() < itemStockDto.getNum()) {
                        throw new BizException(itemStockDto.getItemId() + ": 库存不足");
                    }
                    TbItem item = new TbItem();
                    item.setId(itemStockDto.getItemId());
                    item.setNum(itemStockDto.getNum());
                    int row = tbItemMapper.decreaseStock(item);
                    if (row <= 0) {
                        throw new BizException(itemStockDto.getItemId() + ": 库存不足");
                    }
                    return;
                }
            });
        });
        return true;
    }
}
