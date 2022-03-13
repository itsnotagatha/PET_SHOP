package com.agata.petshop.service;

import com.agata.petshop.model.Item;
import com.agata.petshop.repository.ItemRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    public Item findById(long id) {
        return itemRepository.findById(id);
    }

    public Page<Item> findPaginated(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        return itemRepository.findAll(pageable);
    }

    public Page<Item> findPaginatedByName(int pageNo, int pageSize, String itemName) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        return itemRepository.findByNameContaining(itemName, pageable);
    }

    public List<Item> findAllByName(String name) {
        return itemRepository.findAllByName(name);
    }

    public List<Item> findAllByOrderByNameAsc() {
        return itemRepository.findAllByOrderByNameAsc();
    }

    public void saveItem(Item item) {
        itemRepository.save(item);
    }

    public void deleteItem(long id) {
        itemRepository.delete(itemRepository.findById(id));
    }

    public void editItem(long id, Item newItem) {
        System.out.println(newItem.toString());
        Item found = itemRepository.getById(id);
        found.setName(newItem.getName());
        found.setColor(newItem.getColor());
        found.setPrice(newItem.getPrice());
        saveItem(newItem);
    }

    public long countItems() {
        return itemRepository.count();
    }


    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }
}
