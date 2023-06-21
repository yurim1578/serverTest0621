package com.project.metasu.item.domain.dto;

import com.project.metasu.item.domain.entity.ItemDetail;
import com.project.metasu.item.domain.entity.ItemImg;
import com.project.metasu.item.domain.entity.ItemMaster;
import com.project.metasu.item.domain.entity.ItemStock;
import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter@ToString
public class AdminItemDto {
   UUID id=UUID.randomUUID();

  private String itemCode;
  private String itemName;  //
  private int itemPrice;  //
  private String itemSize;  //
  private int itemWeight; //
  private String itemWaterMethod; //
  private String itemTankCapacity;  //
  private String itemFrom; //
  private String itemIntalType; //
  private String item_desc;  //
  private String itemMasterImg; //
  private LocalDateTime itemMakeDate;

  private int stockNum;


  private String itemColorCode;
    private String itemImg1;
    private String itemImg2;
    private String itemImg3;


  private String itemBarcode="IB__" + LocalDateTime.now() + "_"+ id; //item_stock->자동 추가

  private Boolean salesYn=false;  //


  @Builder
  public AdminItemDto(String itemCode,String itemName, int itemPrice, String itemSize, int itemWeight, String itemWaterMethod, String itemTankCapacity
      , String itemFrom, String itemIntalType, String item_desc, String itemMasterImg,LocalDateTime itemMakeDate,String itemColorCode,String itemImg1,
                      String itemImg2,String itemImg3,int stockNum
     ) {
    this.itemCode = "IC_" + LocalDateTime.now(); //key
    this.itemName = itemName;
    this.itemPrice = itemPrice;
    this.itemSize = itemSize;
    this.itemWeight = itemWeight;
    this.itemWaterMethod = itemWaterMethod;
    this.itemTankCapacity = itemTankCapacity;
    this.itemFrom = itemFrom;
    this.itemIntalType = itemIntalType;
    this.item_desc = item_desc;
    this.itemMasterImg = itemMasterImg;
    this.itemMakeDate =LocalDateTime.now();

    this.itemColorCode=itemColorCode;
    this.itemImg1=itemImg1;
    this.itemImg2=itemImg2;
    this.itemImg3=itemImg3;

    this.stockNum=stockNum;

  }

  public ItemMaster toMEntity(){
    ItemMaster build = ItemMaster.builder()
        .itemCode(itemCode)
        .itemName(itemName)
        .item_desc(item_desc)
        .itemFrom(itemFrom)
        .itemSize(itemSize)
        .itemIntalType(itemIntalType)
        .itemMakeDate(itemMakeDate)  //제조일 자동입력
        .itemPrice(itemPrice)
        .itemTankCapacity(itemTankCapacity)
        .itemMasterImg(itemMasterImg)
        .itemWaterMethod(itemWaterMethod)
        .itemWeight(itemWeight)
        .build();
    return build;
  }
  public ItemDetail toDEntity(String itemCode,String itemColorCode){
    return ItemDetail.builder()
        .itemCode(itemCode)
        .itemColorCode(itemColorCode)
        .build();
  }
  public ItemImg toIEntity(String itemCode,String itemColorCode){

      ItemImg tmp=ItemImg.builder()
          .itemImg1(itemImg1)
          .itemImg2(itemImg2)
          .itemImg3(itemImg3)
          .itemColorCode(itemColorCode)
          .itemMasterImg(itemMasterImg)
          .itemCode(itemCode)
          .build();

    return tmp;
  }
  public ItemStock toSEntity(String itemColorCode){

    return ItemStock.builder()
        .itemBarcode(itemBarcode)
        .itemMaster(toMEntity())
        .itemColorCode(itemColorCode)
        .build();
  }

  public void setItemName(String itemName) {
    this.itemName = itemName;
  }

  public void setItemPrice(int itemPrice) {
    this.itemPrice = itemPrice;
  }

  public void setItemSize(String itemSize) {
    this.itemSize = itemSize;
  }

  public void setItemWeight(int itemWeight) {
    this.itemWeight = itemWeight;
  }

  public void setItemWaterMethod(String itemWaterMethod) {
    this.itemWaterMethod = itemWaterMethod;
  }

  public void setItemTankCapacity(String itemTankCapacity) {
    this.itemTankCapacity = itemTankCapacity;
  }

  public void setItemFrom(String itemFrom) {
    this.itemFrom = itemFrom;
  }

  public void setItemIntalType(String itemIntalType) {
    this.itemIntalType = itemIntalType;
  }

  public void setStockNum(int stockNum) {
    this.stockNum = stockNum;
  }

  public void setItemMasterImg(String itemMasterImg) {
    this.itemMasterImg = itemMasterImg;
  }


  //자동 삽입
  public void setItemCode(String itemCode) {
    this.itemCode = itemCode;
  }



  }





