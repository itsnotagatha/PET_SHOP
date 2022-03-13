package com.agata.petshop.service;

import com.agata.petshop.model.Item;
import com.agata.petshop.model.Purchase;
import com.agata.petshop.model.PurchaseDetails;
import com.agata.petshop.model.User;
import com.agata.petshop.repository.PurchaseDetailsRepository;
import com.agata.petshop.repository.PurchaseRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class PurchaseService {

    private final PurchaseRepository purchaseRepository;
    private final PurchaseDetailsRepository purchaseDetailsRepository;
    private final UserService userService;


    public Map<Purchase, Map<Item, Integer>> getPurchases(String username) {
        User user = userService.findByUsername(username);
        Map<Purchase, Map<Item, Integer>> purchaseMap = new HashMap<>();
        List<Purchase> purchases = new ArrayList<>();

        System.out.println(user.getRole());
        if (user.getRole().equals("ADMIN")) {
            purchases = purchaseRepository.findAll();
        }
        else if (user.getRole().equals("USER")) {
            purchases = purchaseRepository.findAllByUserId(user.getId());
        }

        for (Purchase purchase: purchases) {
            Map<Item, Integer> noOfItemsMap = new HashMap<>();
            for (PurchaseDetails purchaseDetails :purchase.getPurchaseDetails()){
                noOfItemsMap.put(purchaseDetails.getItem(), purchaseDetails.getAmount());
            }
            purchaseMap.put(purchase, noOfItemsMap);
        }

        return purchaseMap;

//            List<Purchase> purchases = purchaseRepository.findAllByUserId(user.getId());
//
//            for (Purchase purchase: purchases) {
//                purchaseMap.put(purchase, purchase.getPurchaseDetails());
//            }
//            return purchaseMap;

    }


    // save a whole order using a map retrieved from the CartService
    public void savePurchase(Map<Item, Integer> cart, String username){
        // https://stackoverflow.com/questions/8625150/why-use-returned-instance-after-save-on-spring-data-jpa-repository
        Purchase purchase = new Purchase();
        purchase.setUser(userService.findByUsername(username));
        purchase = purchaseRepository.save(purchase);

        double totalPrice=0;
        List<PurchaseDetails> purchaseDetailsList = new ArrayList<>();
        for (Item item : cart.keySet()){

            for (int i=0; i<cart.get(item); i++) {
                PurchaseDetails purchaseDetails = new PurchaseDetails();
                purchaseDetails.setItem(item);
                purchaseDetails.setAmount(cart.get(item));
                purchaseDetails.setPurchase(purchase);
                purchaseDetailsList.add(purchaseDetails);
            }
            totalPrice += item.getPrice() * cart.get(item);
        }

        purchaseDetailsList = purchaseDetailsRepository.saveAll(purchaseDetailsList);
        purchase.setPurchaseDetails(purchaseDetailsList);
        purchase.setPrice(totalPrice);
        purchaseRepository.save(purchase);


    }


    public PurchaseService(PurchaseRepository purchaseRepository, PurchaseDetailsRepository purchaseDetailsRepository, UserService userService) {
        this.purchaseRepository = purchaseRepository;
        this.purchaseDetailsRepository = purchaseDetailsRepository;
        this.userService = userService;
    }
}
