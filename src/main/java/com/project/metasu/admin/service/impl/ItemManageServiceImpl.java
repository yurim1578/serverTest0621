package com.project.metasu.admin.service.impl;

import com.project.metasu.admin.repository.AdminItemDetailRepository;
import com.project.metasu.admin.repository.AdminItemImgRepository;
import com.project.metasu.admin.repository.AdminItemRepository;
import com.project.metasu.admin.repository.AdminItemStockRepository;
import com.project.metasu.admin.service.ItemManageService;
import com.project.metasu.item.domain.dto.AdminItemDto;
import com.project.metasu.item.domain.entity.ItemImg;
import com.project.metasu.item.domain.entity.ItemMaster;
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

//하나의 트랜잭션에서 insert,update처리하기

  @Transactional@Override
  public ResponseEntity setItem(AdminItemDto im) {
    adminItemRepository.save(im.toMEntity());
    if(im.getStockNum()!=0) {
      //색상1개
      detailRepository.save(im.toDEntity(im.getItemCode(), im.getItemColorCode()));
      imageRepository.save(im.toIEntity(im.getItemCode(), im.getItemColorCode()));

      for (int j = 0; j < im.getStockNum(); j++) {

        stockRepository.save(im.toSEntity(im.getItemColorCode()));
      }

    }
    return ResponseEntity.ok(HttpStatus.OK.value());
  }


}


