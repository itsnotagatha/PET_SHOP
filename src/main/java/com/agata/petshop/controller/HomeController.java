package com.agata.petshop.controller;

import com.agata.petshop.model.Item;
import com.agata.petshop.service.CategoryService;
import com.agata.petshop.service.ItemService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class HomeController {
    private final ItemService itemService;
    private final CategoryService categoryService;

    private String searchTerm;

    @RequestMapping(value = {"/","/home"})
    public String home(Model model, String searchTerm){
        this.searchTerm = searchTerm;

        return findPaginated(1, model);
    }

    @GetMapping(path = "/home/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo, Model model) {
        // Paginacja: https://www.javaguides.net/2020/06/pagination-and-sorting-with-spring-boot-thymeleaf-spring-data-jpa-hibernate-mysql.html
        int pageSize = 5;
        Page<Item> page;

        if (searchTerm == null)
            page = itemService.findPaginated(pageNo, pageSize);
        else
            page = itemService.findPaginatedByName(pageNo, pageSize, searchTerm);

        List<Item> listItems = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("items", listItems);
        return "home";
    }

    public HomeController(ItemService itemService, CategoryService categoryService) {
        this.itemService = itemService;
        this.categoryService = categoryService;
    }
}

// bez filtrowania:

//    @GetMapping(value = {"/","/home"})
//    public String home(Model model){
//        return findPaginated(1, model);
//    }
//
//    @GetMapping(value = "/home/{pageNo}")
//    public String findPaginated(@PathVariable(value = "pageNo") int pageNo, Model model) {
//        int pageSize = 5;
//        // Paginacja: https://www.javaguides.net/2020/06/pagination-and-sorting-with-spring-boot-thymeleaf-spring-data-jpa-hibernate-mysql.html
//        Page<Item> page = itemService.findPaginated(pageNo, pageSize);
//        List<Item> listItems = page.getContent();
//
//        model.addAttribute("currentPage", pageNo);
//        model.addAttribute("totalPages", page.getTotalPages());
//        model.addAttribute("totalItems", page.getTotalElements());
//        model.addAttribute("items", listItems);
//        return "home";
//    }
