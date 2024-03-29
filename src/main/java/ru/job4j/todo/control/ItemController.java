package ru.job4j.todo.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.todo.model.Item;
import ru.job4j.todo.model.User;
import ru.job4j.todo.service.CategoryService;
import ru.job4j.todo.service.ItemService;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
public class ItemController {

    private final ItemService itemService;
    private final CategoryService categoryService;

    public ItemController(ItemService itemService, CategoryService categoryService) {
        this.itemService = itemService;
        this.categoryService = categoryService;
    }

    @GetMapping("/index")
    public String index(Model model, HttpSession session) {
        User user = userCheck(session);
        model.addAttribute("items", itemService.findAll());
        model.addAttribute("user", user);
        return "index";
    }

    @GetMapping("/itemsCompleted")
    public String itemsCompleted(Model model, HttpSession session) {
        User user = userCheck(session);
        model.addAttribute("user", user);
        model.addAttribute("items", itemService.findCompleted());
        return "index";
    }

    @GetMapping("itemsNew")
    public String itemsNew(Model model, HttpSession session) {
        User user = userCheck(session);
        model.addAttribute("user", user);
        model.addAttribute("items", itemService.findNew());
        return "index";
    }

    @GetMapping("/addItem")
    public String formAddPost(Model model, HttpSession session) {
        User user = userCheck(session);
        model.addAttribute("user", user);
        model.addAttribute("categories", categoryService.getAll());
        return "addItem";
    }

    @PostMapping("/createItem")
    public String createItem(@ModelAttribute Item item, HttpSession session,
                             @RequestParam(name = "catIds") List<String> idsCat) {
        item.setUser((User) session.getAttribute("user"));
        item.setCreated(new Date(System.currentTimeMillis()));
        itemService.create(item, idsCat);
        return "redirect:/index";
    }

    @PostMapping("/updateItem")
    public String updateItem(@ModelAttribute Item item) {
        item.setCreated(new Date(System.currentTimeMillis()));
        itemService.update(item);
        return "redirect:/index";
    }

    @GetMapping("/formUpdateItem/{itemId}")
    public String formUpdateItem(Model model, @PathVariable("itemId") int id, HttpSession session) {
        User user = userCheck(session);
        model.addAttribute("user", user);
        model.addAttribute("item", itemService.findById(id));
        return "updateItem";
    }

    @GetMapping("/formEditItem/{itemId}")
    public String formEditItem(Model model, @PathVariable("itemId") int id, HttpSession session) {
        User user = userCheck(session);
        model.addAttribute("user", user);
        model.addAttribute("item", itemService.findById(id));
        return "item";
    }

    @GetMapping("/doneItem/{itemId}")
    public String doneItem(@PathVariable("itemId") int id) {
        itemService.updateById(id);
        return "redirect:/formEditItem/" + id;
    }

    @GetMapping("/deleteItem/{itemId}")
    public String deleteItem(@PathVariable("itemId") int id) {
        itemService.deleteById(id);
        return "redirect:/index";
    }

    private User userCheck(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            user = new User();
            user.setEmail("Гость");
        }
        return user;
    }
}
