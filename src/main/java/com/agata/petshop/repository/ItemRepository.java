package com.agata.petshop.repository;

import com.agata.petshop.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    Item findById(long Id);
    Item findByName(String name);
    List<Item> findAllByOrderByNameAsc();
    List<Item> findAllByCategoryId(long categoryId);
}
