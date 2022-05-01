package ru.job4j.todo.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.job4j.todo.model.Item;
import ru.job4j.todo.service.ItemService;
import java.time.LocalDateTime;

@Controller
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/items")
    public String items(Model model) {
        model.addAttribute("items", itemService.findAll());
        return "items";
    }

    @GetMapping("/addItem")
    public String formAddPost(Model model) {
        model.addAttribute("item", new Item());
        return "addItem";
    }

    @PostMapping("/createItem")
    public String createPost(@ModelAttribute Item item) {
        itemService.create(item);
        return "redirect:/items";
    }



}
