package com.agata.petshop.controller;


import com.agata.petshop.service.PurchaseService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PurchaseController {

    private final PurchaseService purchaseService;

    @GetMapping(value = "purchases")
    public String getPurchasesTest(Model model) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("purchases", purchaseService.getPurchases(username));
        return "purchases";
    }


    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }
}
