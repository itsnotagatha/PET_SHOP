package com.agata.petshop.controller;

import com.agata.petshop.model.Item;
import com.agata.petshop.service.CategoryService;
import com.agata.petshop.service.ItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {
    private final ItemService itemService;
    private final CategoryService categoryService;

    @GetMapping(value = {"/","/home"})
    public String home(Model model){
        model.addAttribute("items", itemService.findAllByOrderByNameAsc());
        model.addAttribute("categories", categoryService.findAll());
        return "home";
    }

    public HomeController(ItemService itemService, CategoryService categoryService) {
        this.itemService = itemService;
        this.categoryService = categoryService;
    }
}
