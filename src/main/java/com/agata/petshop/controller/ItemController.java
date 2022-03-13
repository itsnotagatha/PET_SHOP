package com.agata.petshop.controller;


import com.agata.petshop.model.Item;
import com.agata.petshop.service.CategoryService;
import com.agata.petshop.service.ItemService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ItemController {

    private final ItemService itemService;
    private final CategoryService categoryService;


    @GetMapping("/item/new")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String newItem(Model model) {
        model.addAttribute("itemForm", new Item());
        model.addAttribute("method", "new");
        model.addAttribute("categories", categoryService.findAll());
        return "item";
    }

    @PostMapping("/item/new")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String newItem(@ModelAttribute("itemForm") Item itemForm, Model model) {
        itemService.saveItem(itemForm);
        return "redirect:/home";
    }

    @GetMapping("/item/edit/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String editItem(@PathVariable("id") long itemId, Model model){
        Item item = itemService.findById(itemId);
        if (item != null){
            model.addAttribute("itemForm", item);
            model.addAttribute("method", "edit");
            model.addAttribute("categories", categoryService.findAll());
            return "item";
        }else {
            return "error/404";
        }
    }

    @PostMapping("/item/edit/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String editItem(@PathVariable("id") long itemId, @ModelAttribute("itemForm") Item itemForm, Model model){
        System.out.println("test");
        itemService.editItem(itemId, itemForm);
        return "redirect:/home";
    }

    @GetMapping("/item/delete/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String deleteItem(@PathVariable("id") long itemId){
        System.out.println("delete item " + itemId);
        Item item = itemService.findById(itemId);
        if (item != null){
            itemService.deleteItem(itemId);
            return "redirect:/home";
        }else {
            return "error/404";
        }
    }


    public ItemController(ItemService itemService, CategoryService categoryService) {
        this.itemService = itemService;
        this.categoryService = categoryService;
    }
}
