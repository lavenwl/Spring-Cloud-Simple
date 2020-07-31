package com.laven.clients;

import com.laven.api.R;
import com.laven.dto.ItemStockDto;
import com.laven.vo.ItemDetailVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.awt.*;
import java.util.List;

@FeignClient(value = "goods-service")
public interface IGoodsFeignClient {

    /**
     * 根据商品id, 查询商品列表
     * @param ids
     * @return
     */
    @GetMapping("items/{ids}")
    R<List<ItemDetailVo>> getItemsByIds(@PathVariable("ids") List<Long> ids);

    @PutMapping(value = "/item/stock", consumes = MediaType.APPLICATION_JSON_VALUE)
    R decreaseStock(@RequestBody List<ItemStockDto> itemStockDtos);
}
