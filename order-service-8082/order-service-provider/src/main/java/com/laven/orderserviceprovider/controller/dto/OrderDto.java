package com.laven.orderserviceprovider.controller.dto;

import com.laven.exception.ValidException;
import lombok.Data;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class OrderDto {
    @NotNull(message = "name 不能为空")
    private String name;
    @NotNull(message = "tel 不能为空")
    private String tel;
    private String userId;
    @NotEmpty(message = "商品列表为空")
    private List<ItemDto> items;

    public void validdData(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder stringBuilder = new StringBuilder();
            for (ObjectError oe : bindingResult.getAllErrors()) {
                stringBuilder.append(oe.getDefaultMessage() + "\n");
            }
            throw new ValidException(stringBuilder.toString());
        }
    }
}
