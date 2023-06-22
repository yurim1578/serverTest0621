package com.project.metasu.home.Dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HomeItemDto {

    String itemCode;
    String itemName;
    int itemPrice;
    String itemMasterImg;
    String itemColorCode;
    LocalDateTime createdDate;


  }


