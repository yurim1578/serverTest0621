package com.project.metasu.item.repository;

import com.project.metasu.item.domain.entity.ItemDetailId;
import com.project.metasu.item.domain.entity.ItemImg;
import com.project.metasu.item.domain.entity.ItemStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ItemStockRepository extends JpaRepository<ItemStock, String> {

    @Query(value = "select item_barcode from item_stock "
            + "where item_code = :itemCode "
            + "and item_color_code = :itemColorCode "
            + "and sales_yn = :salesYn "
            + "order by created_date desc limit :orderQty", nativeQuery = true)
    List<Map<String,Object>> findByItemBarcode(@Param("itemCode") String itemCode,
                                               @Param("itemColorCode") String itemColorCode,
                                               @Param("salesYn") String salesYn,
                                               @Param("orderQty") int orderQty);

}