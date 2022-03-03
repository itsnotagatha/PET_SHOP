package com.agata.petshop;

import com.agata.petshop.model.Category;
import com.agata.petshop.model.Item;
import com.agata.petshop.repository.CategoryRepository;
import com.agata.petshop.repository.ItemRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Component
public class DataLoader implements ApplicationRunner {
    private final ItemRepository itemRepository;
    private final CategoryRepository categoryRepository;

    public void run(ApplicationArguments args) {

        Category newCategory = new Category("obroże", new HashSet<Item>());
        Item newItem = new Item("obroża", "żółta", 5.6, newCategory);

        categoryRepository.save(newCategory);
        itemRepository.save(newItem);
    }

    public DataLoader(ItemRepository itemRepository, CategoryRepository categoryRepository) {
        this.itemRepository = itemRepository;
        this.categoryRepository = categoryRepository;
    }
}
