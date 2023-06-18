package com.project.metasu.item.repository;

import com.project.metasu.item.domain.entity.Cart;
import com.project.metasu.item.domain.entity.CartId;
import com.project.metasu.item.domain.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface ReviewRepository  extends JpaRepository<Review, Long> {
    @Query(value = "select r.review_no, r.created_date, r.review_contents, r.review_score, r.review_title, m.member_id, im.item_code, im.item_name, idl.item_color_code, ccd.sub_code_name as item_color_code_name "
            + "from review r left join item_master im "
            + "on r.item_code = im.item_code "
            + "left join item_detail idl on r.item_color_code = idl.item_color_code "
            + "left join member m on r.member_id = m.member_id "
            + "left join common_code_detail ccd on idl.item_color_code = ccd.sub_code "
            + "where im.item_code = :itemCode", nativeQuery = true)
    List<Map<String,Object>> findReviewByItemCode(@Param("itemCode") String itemCode);

    Page<Review> findByItemCodeItemCode(String itemCode, Pageable pageable);





}
