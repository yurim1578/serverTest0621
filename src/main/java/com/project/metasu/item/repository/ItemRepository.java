package com.project.metasu.item.repository;

import com.project.metasu.item.domain.entity.ItemMaster;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<ItemMaster, String> {


}
