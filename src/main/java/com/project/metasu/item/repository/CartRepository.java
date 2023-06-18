package com.project.metasu.item.repository;

import com.project.metasu.item.domain.entity.Cart;
import com.project.metasu.item.domain.entity.CartId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, CartId> {
    Optional<Cart> findByItemCodeAndItemColorCodeAndMemberId(String itemCode, String itemColorCode, String memberId);
    Boolean existsByItemCodeAndItemColorCodeAndMemberId(String itemCode, String itemColorCode, String memberId);
    @Query(value = "SELECT c.item_code, c.item_color_code, c.cart_qty, i.item_master_img, m.item_price, m.item_name "
            + "FROM cart c LEFT JOIN item_master m ON c.item_code = m.item_code "
            + "LEFT JOIN item_img i ON c.item_code = i.item_code AND c.item_color_code = i.item_color_code "
            + "WHERE c.member_id = :memberId", nativeQuery = true)
    List<Map<String,Object>> findCart(@Param("memberId") String memberId);

    void deleteByItemCodeAndItemColorCodeAndMemberId(String itemCode, String itemColorCode, String memberId);
    void deleteByMemberId(String memberId);

    @Query(value = "select c.item_code, c.item_color_code, cd.sub_code_name as item_color_code_name , c.cart_qty, m.item_price, m.item_name "
            + "from cart c left join item_master m on c.item_code = m.item_code "
            + "left join item_img i on c.item_code = i.item_code and c.item_color_code = i.item_color_code "
            + "left join common_code_detail cd on c.item_color_code = cd.sub_code "
            + "WHERE cd.code = 'IC' and c.member_id = :memberId", nativeQuery = true)
    List<Map<String,Object>> findAllByMemberId(@Param("memberId") String memberId);

}
