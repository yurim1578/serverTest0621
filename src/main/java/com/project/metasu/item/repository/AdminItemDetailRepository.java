package com.project.metasu.item.repository;

import com.project.metasu.item.domain.entity.ItemDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface AdminItemDetailRepository extends JpaRepository<ItemDetail,String> {
  ItemDetail findByItemCode(@Param("item_code") String itemCode);
}
