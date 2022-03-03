package com.agata.petshop.controller;

import com.agata.petshop.model.Item;
import com.agata.petshop.service.CartService;
import com.agata.petshop.service.ItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CartController {

    private final ItemService itemService;
    private final CartService cartService;

    @GetMapping("/cart")
    public String cart(Model model){
        model.addAttribute("items", cartService.itemsInCart());
        model.addAttribute("totalPrice", cartService.totalPrice());
        return "cart";
    }

    @GetMapping("/cart/add/{id}")
    public String addToCart(@PathVariable("id") long id){
        Item item = itemService.findById(id);
        if (item != null){
            cartService.addItem(item);
        }
        return "redirect:/home";
    }

    @GetMapping("/cart/remove/{id}")
    public String removeFromCart(@PathVariable("id") long id){
        Item product = itemService.findById(id);
        if (product != null){
            cartService.removeItem(product);
        }
        return "redirect:/cart";
    }

    @GetMapping("/cart/purchase")
    public String cartPurchase(){
        cartService.cartPurchase();
        return "redirect:/cart";
    }


    public CartController(ItemService itemService, CartService cartService) {
        this.itemService = itemService;
        this.cartService = cartService;
    }
}
