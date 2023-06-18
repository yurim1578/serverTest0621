package com.project.metasu.item.domain.dto;

import com.project.metasu.item.domain.entity.ItemDetail;
import com.project.metasu.item.domain.entity.ItemImg;
import com.project.metasu.item.domain.entity.ItemMaster;
import com.project.metasu.item.domain.entity.ItemStock;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter@ToString
public class AdminItemDto {
  private String itemCode;  //
  private String itemName;  //
  private int itemPrice;  //
  private String itemSize;  //
  private int itemWeight; //
  private String itemWaterMethod; //
  private String itemTankCapacity;  //
  private String itemFrom; //
  private String itemIntalType; //
  private String itemDesc;  //
  private String itemMasterImg; //
  @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
  private LocalDateTime itemMakeDate; //

  private String itemColorCode; //
  private String itemImg1;
  private String itemImg2;
  private String itemImg3;

  private String itemBarcode; //
  private Boolean salesYn;  //
  private LocalDateTime salesDate;

  @Builder
  public AdminItemDto(String itemCode, String itemName, int itemPrice, String itemSize, int itemWeight, String itemWaterMethod, String itemTankCapacity, String itemFrom, String itemIntalType, String itemDesc, String itemMasterImg, LocalDateTime itemMakeDate, String itemColorCode, String itemImg1, String itemImg2, String itemImg3, String itemBarcode, Boolean salesYn, LocalDateTime salesDate) {
    this.itemCode = itemCode; //key
    this.itemName = itemName;
    this.itemPrice = itemPrice;
    this.itemSize = itemSize;
    this.itemWeight = itemWeight;
    this.itemWaterMethod = itemWaterMethod;
    this.itemTankCapacity = itemTankCapacity;
    this.itemFrom = itemFrom;
    this.itemIntalType = itemIntalType;
    this.itemDesc = itemDesc;
    this.itemMasterImg = itemMasterImg;
    this.itemMakeDate = itemMakeDate;
    this.itemColorCode = itemColorCode; //key
    this.itemImg1 = itemImg1;
    this.itemImg2 = itemImg2;
    this.itemImg3 = itemImg3;
    this.itemBarcode = itemBarcode; //key
    this.salesYn = salesYn;
    this.salesDate = salesDate; //19개-key=16개
  }

  public ItemMaster toMEntity(){
    ItemMaster build = ItemMaster.builder()
        .itemCode(itemCode)
        .itemName(itemName)
        .item_desc(itemDesc)
        .itemFrom(itemFrom)
        .itemSize(itemSize)
        .itemIntalType(itemIntalType)
        .itemMakeDate(itemMakeDate)
        .itemPrice(itemPrice)
        .itemTankCapacity(itemTankCapacity)
        .itemMasterImg(itemMasterImg)
        .itemWaterMethod(itemWaterMethod)
        .itemWeight(itemWeight)
        .build();
    return build;
  }
  public ItemDetail toDEntity(){
    return ItemDetail.builder()
        .itemCode(itemCode)
        .itemColorCode(itemCode)
        .build();
  }
  public ItemImg toIEntity(){
    return ItemImg.builder()
        .itemCode(itemCode)
        .itemColorCode(itemColorCode)
        .itemMasterImg(itemMasterImg)
        .itemImg1(itemImg1)
        .itemImg2(itemImg2)
        .itemImg3(itemImg3)
        .build();
  }
  public ItemStock toSEntity(){
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

  public void setItemDesc(String itemDesc) {
    this.itemDesc = itemDesc;
  }

  public void setItemMasterImg(String itemMasterImg) {
    this.itemMasterImg = itemMasterImg;
  }

  public void setItemMakeDate(LocalDateTime itemMakeDate) {
    this.itemMakeDate = itemMakeDate;
  }

  public void setItemImg1(String itemImg1) {
    this.itemImg1 = itemImg1;
  }

  public void setItemImg2(String itemImg2) {
    this.itemImg2 = itemImg2;
  }

  public void setItemImg3(String itemImg3) {
    this.itemImg3 = itemImg3;
  }

  public void setSalesYn(Boolean salesYn) {
    this.salesYn = salesYn;
  }

  public void setSalesDate(LocalDateTime salesDate) {
    this.salesDate = salesDate;
  }
}





