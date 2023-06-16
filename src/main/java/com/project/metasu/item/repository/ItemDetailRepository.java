package com.project.metasu.item.repository;

import com.project.metasu.item.domain.entity.ItemDetail;
import com.project.metasu.item.domain.entity.ItemDetailId;
import com.project.metasu.item.domain.entity.ItemImg;
import com.project.metasu.item.domain.entity.ItemMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ItemDetailRepository extends JpaRepository<ItemDetail, ItemDetailId> {
    List<ItemDetail> findAllByItemCode(String itemCode);
    Optional<ItemDetail> findByItemCodeAndItemColorCode(String itemCode, String itemColorCode);
}