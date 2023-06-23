package com.project.metasu.item.repository;

import com.project.metasu.item.domain.entity.Contract;
import com.project.metasu.item.domain.entity.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ContractRepository extends JpaRepository<Contract, String> {
   /* @Query("SELECT c FROM Contract c WHERE c.contract_status = :contractStatus")
    List<Contract> findByContractStatus(@Param("contractStatus") String contractStatus);*/

    List<Contract> findByContractStatus(String contractStatus);

}
