package com.agata.petshop.repository;

import com.agata.petshop.model.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    Item findById(long Id);
    //Item findByName(String name);
    Page<Item> findByNameContaining(String name, Pageable pageable);
    Page<Item> findAllByName(Pageable pageable, String name);
    List<Item> findAllByName(String name);
    List<Item> findAllByOrderByNameAsc();
}
