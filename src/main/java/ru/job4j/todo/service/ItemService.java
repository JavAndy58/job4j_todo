package ru.job4j.todo.service;

import org.springframework.stereotype.Service;
import ru.job4j.todo.model.Item;
import ru.job4j.todo.store.ItemStore;

import java.util.Collection;

@Service
public class ItemService {

    private final ItemStore store;

    public ItemService(ItemStore store) {
        this.store = store;
    }

    public Collection<Item> findAll() {
        return store.findAll();
    }

    public void create(Item item) {
        store.create(item);
    }

//    public void update(Item item) {
//        store.update(item);
//    }
//
//    public Item findById(int id) {
//        return store.findById(id);
//    }
}
