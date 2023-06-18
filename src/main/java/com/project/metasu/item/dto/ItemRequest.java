package com.project.metasu.item.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
@NoArgsConstructor
public class ItemRequest {
    private String itemCode;
    private String itemName;
    private int itemPrice;
    private String itemSize;
    private int itemWeight;
    private String itemWaterMethod;
    private String itemTankCapacity;
    private String itemIntalType;
    private LocalDateTime itemMakeDate;
    private String itemFrom;
    private String itemMasterImg;
}
