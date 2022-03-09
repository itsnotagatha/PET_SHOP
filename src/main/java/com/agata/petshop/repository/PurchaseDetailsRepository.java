package com.agata.petshop.repository;

import com.agata.petshop.model.PurchaseDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseDetailsRepository extends JpaRepository<PurchaseDetails, Long> {

    List<PurchaseDetails> findAllByPurchaseId(long id);

}
