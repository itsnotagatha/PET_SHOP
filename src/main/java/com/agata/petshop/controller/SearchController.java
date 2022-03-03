package com.agata.petshop.controller;

import com.agata.petshop.service.CategoryService;
import com.agata.petshop.service.ItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class SearchController {
    private final ItemService itemService;
    private String keyword;

    @GetMapping(value = {"/search"})
    public String searchPage(Model model){
        model.addAttribute("keyword", keyword);
        model.addAttribute("items", itemService.findAllByName(keyword));
        return "search";
    }

    @RequestMapping(path = "/searchItem")
    public String search(Model model, String keyword) {
        this.keyword = keyword;
        return "redirect:/search";
    }

    public SearchController(ItemService itemService, CategoryService categoryService) {
        this.itemService = itemService;
    }
}
