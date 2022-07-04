package ru.job4j.todo.service;

import org.springframework.stereotype.Service;
import ru.job4j.todo.model.Item;
import ru.job4j.todo.store.ItemStore;
import java.util.Collection;
import java.util.List;

@Service
public class ItemService {

    private final ItemStore store;

    public ItemService(ItemStore store) {
        this.store = store;
    }

    public Collection<Item> findAll() {
        return store.findAll();
    }

    public Collection<Item> findCompleted() {
        return store.findCompleted();
    }

    public Collection<Item> findNew() {
        return store.findNew();
    }

    public void create(Item item, List<String> idsCat) {
        store.create(item, idsCat);
    }

    public void update(Item item) {
        store.update(item);
    }

    public Item findById(int id) {
        return store.findById(id);
    }

    public void updateById(int id) {
        store.updateById(id);
    }

    public void deleteById(int id) {
        store.deleteById(id);
    }

}