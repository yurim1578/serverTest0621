package com.project.metasu.admin.repository;

import com.project.metasu.item.domain.entity.ItemImg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AdminItemImgRepository extends JpaRepository<ItemImg,String> {

  List<ItemImg> findAllByItemCode(@Param(value = "item_code") String itemCode);
  ItemImg findByItemCode(@Param(value = "item_code")String itemCode);
}
