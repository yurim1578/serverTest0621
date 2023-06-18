package com.project.metasu.item.repository;

import com.project.metasu.item.domain.entity.ItemMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ItemRepository extends JpaRepository<ItemMaster, String> {


    Page<ItemMaster> findAll(Pageable pageable);

    @Query("SELECT i FROM ItemMaster i WHERE LOWER(i.itemTankCapacity) LIKE %:capacity%")
    Page<ItemMaster> findByItemTankCapacityContainingIgnoreCase(Pageable pageable, @Param("capacity") String capacity);

    @Query("SELECT i FROM ItemMaster i WHERE LOWER(i.itemIntalType) LIKE %:type%")
    Page<ItemMaster> findByItemIntalTypeContainingIgnoreCase(Pageable pageable, @Param("type") String type);

    @Query("SELECT i FROM ItemMaster i WHERE LOWER(i.itemFrom) LIKE %:from%")
    Page<ItemMaster> findByItemFromContainingIgnoreCase(Pageable pageable, @Param("from") String from);

    @Query("SELECT i FROM ItemMaster i WHERE LOWER(i.itemWaterMethod) LIKE %:method%")
    Page<ItemMaster> findByItemWaterMethodContainingIgnoreCase(Pageable pageable, @Param("method") String method);
}
