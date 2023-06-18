package com.project.metasu.admin.service;

import com.project.metasu.item.domain.dto.AdminItemDto;
import com.project.metasu.item.domain.entity.ItemImg;
import com.project.metasu.item.domain.entity.ItemMaster;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface ItemManageService {
  List<Map<String,Object>> getItemList();
  ItemMaster getItemDetails(String itemCode);
  List<ItemImg> getItemImg(String itemCode);

  List<Map<String,Object>> getStockList(String itemCode);

  List<Map<String,Object>> getStockDetails(String itemCode);

  public ResponseEntity setItem(AdminItemDto im);

//  List<Map<String,Object>> listColors();

//  List<String> listColorNames();
}
