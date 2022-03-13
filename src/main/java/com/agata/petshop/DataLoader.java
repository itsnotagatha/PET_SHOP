package com.agata.petshop;

import com.agata.petshop.model.*;
import com.agata.petshop.repository.CategoryRepository;
import com.agata.petshop.repository.ItemRepository;
import com.agata.petshop.repository.PurchaseDetailsRepository;
import com.agata.petshop.repository.PurchaseRepository;
import com.agata.petshop.service.PurchaseService;
import com.agata.petshop.service.UserService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Component
public class DataLoader implements ApplicationRunner {
    private final ItemRepository itemRepository;
    private final CategoryRepository categoryRepository;
    private final UserService userService;
    private final PurchaseService purchaseService;
    private final PurchaseRepository purchaseRepository;
    private final PurchaseDetailsRepository purchaseDetailsRepository;

    public void run(ApplicationArguments args) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        Category newCategory = new Category("dla psów", new HashSet<Item>());

        List<Item> items = new ArrayList<>();
        items.add(new Item("obroża", "żółty", 5, newCategory));
        items.add(new Item("smycz", "czerwony", 10, newCategory));
        items.add(new Item("obroża", "pomarańczowy", 6, newCategory));
        items.add(new Item("zabawka", "zielony", 17, newCategory));
        items.add(new Item("piłka", "różne kolory", 5, newCategory));
        items.add(new Item("kocyk", "zielony", 25, newCategory));
        items.add(new Item("buciki", "czarny", 10, newCategory));
        items.add(new Item("legowisko", "pomarańczowy", 45, newCategory));


        User user = new User (
            "agata",
            "USER",
            "password",
            "passwordConfirm",
            "email@email.com",
            12341234
        );
        User admin = new User (
                "admin",
                "ADMIN",
                "password",
                "passwordConfirm",
                "adminEmail@email.com",
                12341234
        );
        userService.save(user);
        userService.saveAdmin(admin);
        categoryRepository.save(newCategory);
        itemRepository.saveAll(items);


    }

    public DataLoader(ItemRepository itemRepository, CategoryRepository categoryRepository, UserService userService, PurchaseService purchaseService, PurchaseRepository purchaseRepository, PurchaseDetailsRepository purchaseDetailsRepository) {
        this.itemRepository = itemRepository;
        this.categoryRepository = categoryRepository;
        this.userService = userService;
        this.purchaseService = purchaseService;
        this.purchaseRepository = purchaseRepository;
        this.purchaseDetailsRepository = purchaseDetailsRepository;
    }
}
