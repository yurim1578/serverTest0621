package com.project.metasu.item.repository;

import com.project.metasu.item.domain.entity.Rental;
import com.project.metasu.util.domain.CommonCodeDetail;
import com.project.metasu.util.domain.CommonCodeDetailId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommonCodeDetailRepository extends JpaRepository<CommonCodeDetail, CommonCodeDetailId> {
    List<CommonCodeDetail> findByCode(String code);
}
