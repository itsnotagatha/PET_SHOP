package com.agata.petshop.service;

import com.agata.petshop.model.Category;
import com.agata.petshop.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;


    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public void saveCategory(Category newCategory) {
        categoryRepository.save(newCategory);
    }


    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
}
