package com.agata.petshop;

import com.agata.petshop.model.Category;
import com.agata.petshop.model.Item;
import com.agata.petshop.model.User;
import com.agata.petshop.repository.CategoryRepository;
import com.agata.petshop.repository.ItemRepository;
import com.agata.petshop.service.UserService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;

@Component
public class DataLoader implements ApplicationRunner {
    private final ItemRepository itemRepository;
    private final CategoryRepository categoryRepository;
    private final UserService userService;

    public void run(ApplicationArguments args) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        Category newCategory = new Category("obroże", new HashSet<Item>());
        Item newItem = new Item("obroża", "żółta", 5.6, newCategory);

        User user = new User (
            "agata",
            "ADMIN",
            "password",
            "passwordConfirm",
            "email@email.com",
            12341234
        );
        userService.save(user);


        categoryRepository.save(newCategory);
        itemRepository.save(newItem);
    }

    public DataLoader(ItemRepository itemRepository, CategoryRepository categoryRepository, UserService userService) {
        this.itemRepository = itemRepository;
        this.categoryRepository = categoryRepository;
        this.userService = userService;
    }
}
