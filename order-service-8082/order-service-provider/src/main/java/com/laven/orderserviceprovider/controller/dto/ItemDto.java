package com.laven.orderserviceprovider.controller.dto;

import lombok.Data;

import javax.annotation.sql.DataSourceDefinition;

@Data
public class ItemDto {
    private String itemId;
    private int num;
}
