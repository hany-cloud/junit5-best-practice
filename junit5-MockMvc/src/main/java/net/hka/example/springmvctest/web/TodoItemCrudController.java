package net.hka.example.springmvctest.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import net.hka.example.springmvctest.todo.TodoItemCrudService;
import net.hka.example.springmvctest.todo.TodoItemDTO;
import net.hka.example.springmvctest.todo.TodoListItemDTO;

/**
 * Provides CRUD operations for todo items.
 */
@Controller
@RequestMapping("/todo-item")
public class TodoItemCrudController {

    private final TodoItemCrudService service;

    @Autowired
    public TodoItemCrudController(TodoItemCrudService service) {
        this.service = service;
    }

    /**
     * Renders the HTML view that displays the information of all
     * todo items found from the database.
     * @param model The model that contains the attributes which are
     *              required to render the HTML view.
     * @return  The name of the rendered HTML view.
     */
    @GetMapping
    public String findAll(Model model) {
        List<TodoListItemDTO> todoItems = service.findAll();
        model.addAttribute("todoItems", todoItems);
        return "todo-item/list";
    }

    /**
     * Renders the HTML view that displays the information of the
     * requested todo item.
     * @param id    The id of the requested todo item.
     * @param model The model that contains the attributes which are
     *              required to render the HTML view.
     * @return      The name of the rendered HTML view.
     * @throws net.petrikainulainen.springmvctest.junit5.todo.TodoItemNotFoundException if the requested todo item isn't found from the database.
     */
    @GetMapping("{id}")
    public String findById(@PathVariable("id") Long id, Model model) {
        TodoItemDTO found = service.findById(id);
        model.addAttribute("todoItem", found);
        return "todo-item/view";
    }
}
