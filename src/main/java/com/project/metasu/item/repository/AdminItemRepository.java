package com.project.metasu.item.repository;

import com.project.metasu.item.domain.entity.ItemMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface AdminItemRepository extends JpaRepository<ItemMaster, String> {

  @Query(value="select m.item_name, m.item_code, m.item_price, m.item_intal_type, m.item_make_date, count(*) as 'itemTotalStock' FROM item_master m LEFT JOIN item_stock s ON m.item_code = s.item_code GROUP BY m.item_code",nativeQuery = true)
  List<Map<String,Object>> findItemListDto();


  ItemMaster findByItemCode(@Param(value = "item_code") String itemCode);

}