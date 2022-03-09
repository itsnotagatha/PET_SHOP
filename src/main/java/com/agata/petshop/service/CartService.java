package com.agata.petshop.service;

import com.agata.petshop.model.Item;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CartService {

    private Map<Item, Integer> cart = new LinkedHashMap<>();

    public void addItem(Item item) {
        if (cart.containsKey(item)){
            cart.replace(item, cart.get(item) + 1);
        }else{
            cart.put(item, 1);
        }
    }

    public void removeItem(Item item) {
        if (cart.containsKey(item)) {
            if (cart.get(item) > 1)
                cart.replace(item, cart.get(item) - 1);
            else if (cart.get(item) == 1) {
                cart.remove(item);
            }
        }
    }

    public void clearItems() {
        cart.clear();
    }

    public Map<Item, Integer> itemsInCart() {
        return Collections.unmodifiableMap(cart);
    }

    public double totalPrice() {
        double sum = 0;
        for (Item item : cart.keySet()) {
            sum += item.getPrice() * cart.get(item);
        }
        return sum;
    }

    public void cartPurchase() {

        cart.clear();
    }
}
