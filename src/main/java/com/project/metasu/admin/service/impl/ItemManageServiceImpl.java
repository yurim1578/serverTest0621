package com.project.metasu.admin.service.impl;

import com.project.metasu.admin.service.ItemManageService;
import com.project.metasu.item.domain.dto.AdminItemDto;
import com.project.metasu.item.domain.entity.ItemImg;
import com.project.metasu.item.domain.entity.ItemMaster;
import com.project.metasu.item.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ItemManageServiceImpl implements ItemManageService {

  private final AdminItemRepository adminItemRepository;
  private final AdminItemImgRepository imageRepository;
  private final AdminItemStockRepository stockRepository;
  private final AdminItemDetailRepository detailRepository;

  @Override
  public List<Map<String,Object>> getItemList() {
    List<Map<String,Object>> lists = adminItemRepository.findItemListDto();

    return lists;
  }

  @Override
  public ItemMaster getItemDetails(String code) {
    return adminItemRepository.findByItemCode(code);
  }

  @Override
  public List<ItemImg> getItemImg(String itemCode) {
    return imageRepository.findAllByItemCode(itemCode);
  }

  @Override
  public List<Map<String, Object>> getStockList(String itemCode) {
    return stockRepository.getStockList(itemCode);
  }

  @Override
  public List<Map<String, Object>> getStockDetails(String itemCode) {
    return stockRepository.getColorStock(itemCode);
  }
//하나의 트랜잭션에서 insert처리하기
  @Transactional@Override
  public ResponseEntity setItem(AdminItemDto im){
    System.out.println(im.toString());

    adminItemRepository.save(im.toMEntity());
    imageRepository.save(im.toIEntity());
    detailRepository.save(im.toDEntity());
    stockRepository.save(im.toSEntity());

    return ResponseEntity.ok(HttpStatus.OK.value());
  }



}


