package com.project.metasu.item.repository;

import com.project.metasu.item.domain.entity.ItemMaster;
import com.project.metasu.member.domain.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface ItemMasterRepository extends JpaRepository<ItemMaster, String> {
    // 사용자 ID를 기반으로 항목 세부 정보를 가져오는 메서드->ItemMasterService에서 해당 메소드를 호출
    // 20230618
    // List<ItemMaster> findByMember(Member member);

    Page<ItemMaster> findAll(Pageable pageable);

    @Query("SELECT i FROM ItemMaster i WHERE LOWER(i.itemTankCapacity) LIKE %:capacity%")
    Page<ItemMaster> findByItemTankCapacityContainingIgnoreCase(Pageable pageable, @Param("capacity") String capacity);

    @Query("SELECT i FROM ItemMaster i WHERE LOWER(i.itemIntalType) LIKE %:type%")
    Page<ItemMaster> findByItemIntalTypeContainingIgnoreCase(Pageable pageable, @Param("type") String type);

    @Query("SELECT i FROM ItemMaster i WHERE LOWER(i.itemFrom) LIKE %:from%")
    Page<ItemMaster> findByItemFromContainingIgnoreCase(Pageable pageable, @Param("from") String from);

    @Query("SELECT i FROM ItemMaster i WHERE LOWER(i.itemWaterMethod) LIKE %:method%")
    Page<ItemMaster> findByItemWaterMethodContainingIgnoreCase(Pageable pageable, @Param("method") String method);

    @Query(value = "select m.item_price, m.item_name, c.sub_code_name as item_color_code_name "
            + "from item_master m "
            + "left join item_detail id on m.item_code = id.item_code "
            + "left join common_code_detail c on id.item_color_code = c.sub_code "
            + "where m.item_code = :itemCode and c.code = 'IC' and id.item_color_code = :itemColorCode", nativeQuery = true)
    Map<String,Object> findByRentalOrderInfo(@Param("itemCode") String itemCode,
                                             @Param("itemColorCode") String itemColorCode); // 렌탈시 주문목록에서 보여줘야할 추가 정보들

}

