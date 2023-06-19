package com.project.metasu.admin.repository;

import com.project.metasu.item.domain.entity.ItemStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface AdminItemStockRepository extends JpaRepository<ItemStock,String> {
  @Query(value="select item_code, item_color_code, count(*) as 'stock' from item_stock where sales_yn='n' and item_code=:itemCode group by item_code, item_color_code",nativeQuery = true)
  List<Map<String,Object>> getStockList(@Param("itemCode") String itemCode);

  @Query(value="select c.sub_code_name, s.item_color_code, s.item_code, count(s.item_barcode) as stockCount "+
      "from item_stock s join common_code_detail c on s.item_color_code=c.sub_code "+
      "where c.code='IC' and s.sales_yn='n' and s.item_code=:itemCode "+
      "group by item_code, item_color_code",nativeQuery = true)
  List<Map<String,Object>> getColorStock(@Param("itemCode")String itemCode);

  ItemStock findByItemCode(@Param("item_code")String itemCode);
}
