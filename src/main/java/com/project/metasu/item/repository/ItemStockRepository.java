package com.project.metasu.item.repository;

import com.project.metasu.item.domain.entity.ItemDetailId;
import com.project.metasu.item.domain.entity.ItemImg;
import com.project.metasu.item.domain.entity.ItemStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ItemStockRepository extends JpaRepository<ItemStock, String> {

    /*@Query(value = "select item_barcode from item_stock "
            + "where item_code = :itemCode "
            + "and item_color_code = :itemColorCode "
            + "and sales_yn = :salesYn "
            + "order by created_date desc limit :orderQty", nativeQuery = true)
    List<Map<String,Object>> findByItemBarcode(@Param("itemCode") String itemCode,
                                               @Param("itemColorCode") String itemColorCode,
                                               @Param("salesYn") String salesYn,
                                               @Param("orderQty") int orderQty);*/

    // 구매일때 상품별 컬러별 수량만큼 바코드 추출
    /*@Query(value = "WITH RECURSIVE CTE AS ("
            + "SELECT ("
            + "SELECT item_barcode "
            + "FROM item_stock "
            + "WHERE CONCAT(item_code, '_', item_color_code) = CONCAT(a.item_code, '_', a.item_color_code) "
            + "AND sales_yn = 'N' "
            + "ORDER BY created_date "
            + "LIMIT 1"
            + ") as item_barcode, "
            + "a.item_code, "
            + "a.item_color_code, "
            + "a.cart_qty, "
            + "(a.cart_qty - 1) as maxqty "
            + "FROM cart a "
            + "WHERE a.cart_qty > 0 "
            + "UNION "
            + "SELECT b.item_barcode, "
            + "a.item_code, "
            + "a.item_color_code, "
            + "a.cart_qty, "
            + "(a.maxqty - 1) as maxqty "
            + "FROM CTE a "
            + "INNER JOIN ("
            + "SELECT ROW_NUMBER() OVER (PARTITION BY item_code, item_color_code ORDER BY created_date) as rs, "
            + "item_code, "
            + "item_color_code, "
            + "item_barcode "
            + "FROM item_stock "
            + ") b "
            + "ON a.item_code = b.item_code "
            + "AND a.item_color_code = b.item_color_code "
            + "AND rs = (a.cart_qty - (a.maxqty - 1)) "
            + "WHERE a.maxqty > 0 "
            + ")"
            + "SELECT * "
            + "FROM CTE "
            + "WHERE item_barcode IS NOT NULL", nativeQuery = true)
    List<Map<String,Object>> findByItemBarcode();*/

    @Query(value = "SELECT * FROM item_stock " +
            "WHERE FIND_IN_SET(item_barcode, (" +
            "SELECT GROUP_CONCAT(SUBSTRING_INDEX((" +
            "SELECT GROUP_CONCAT(item_barcode) " +
            "FROM item_stock " +
            "WHERE (item_code, item_color_code) = (a.item_code, a.item_color_code) " +
            "ORDER BY created_date), ',', a.cart_qty)) " +
            "FROM cart a " +
            "WHERE cart_qty > 0 " +
            "AND member_id = :memberId))", nativeQuery = true)
    List<ItemStock> findByItemBarcode(@Param("memberId") String memberId);

    // 렌탈일때 바코드 추출
    ItemStock findTop1ByItemCodeAndItemColorCodeAndSalesYn(String itemCode, String itemColorCode, Boolean salesYn);

}