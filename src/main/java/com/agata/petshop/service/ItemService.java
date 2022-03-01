package com.agata.petshop.service;

import com.agata.petshop.model.Item;
import com.agata.petshop.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    private final ItemRepository itemRepository;


    public List<Item> findAllByOrderByNameAsc() {
        return itemRepository.findAllByOrderByNameAsc();
    }

    public List<Item> findAllByCategoryId(long categoryId) {
        return itemRepository.findAllByCategoryId(categoryId);
    }

    public void saveItem(Item item) {
        itemRepository.save(item);
    }

    public void deleteItem(long id) {
        itemRepository.delete(itemRepository.findById(id));
    }

    public void editItem(long id, Item newItem) {
        this.deleteItem(id);
        itemRepository.save(newItem);
    }

    public long countItems() {
        return itemRepository.count();
    }


    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }
}
