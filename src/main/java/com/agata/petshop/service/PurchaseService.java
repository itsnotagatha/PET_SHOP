package com.agata.petshop.service;

import com.agata.petshop.model.Item;
import com.agata.petshop.model.Purchase;
import com.agata.petshop.model.PurchaseDetails;
import com.agata.petshop.repository.PurchaseDetailsRepository;
import com.agata.petshop.repository.PurchaseRepository;

import java.util.List;
import java.util.Map;

public class PurchaseService {

    private final PurchaseRepository purchaseRepository;
    private final PurchaseDetailsRepository purchaseDetailsRepository;


    // get orders for a user
    public List<Purchase> getPurchases(long userId){

        return null;
    }

    public PurchaseDetails getPurchaseDetails(long purchaseId){

        return null;
    }

    public void savePurchase(Map<Item, Integer> cart, long userId){


    }

    // save a whole order using a map




    public PurchaseService(PurchaseRepository purchaseRepository, PurchaseDetailsRepository purchaseDetailsRepository) {
        this.purchaseRepository = purchaseRepository;
        this.purchaseDetailsRepository = purchaseDetailsRepository;
    }
}
