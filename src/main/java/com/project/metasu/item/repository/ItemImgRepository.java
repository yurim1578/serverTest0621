package com.project.metasu.item.repository;

import com.project.metasu.item.domain.entity.ItemDetail;
import com.project.metasu.item.domain.entity.ItemDetailId;
import com.project.metasu.item.domain.entity.ItemImg;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ItemImgRepository extends JpaRepository<ItemImg, ItemDetailId> {
    List<ItemImg> findTop1ByItemCodeOrderByItemColorCodeDesc(String itemCode);
    Optional<ItemImg> findByItemCodeAndItemColorCode(String itemCode, String itemColorCode);
}