package de.neuefische.backend.controller;

import de.neuefische.backend.model.ToDo;
import de.neuefische.backend.service.ToDoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todo")
public class ToDoController {

    private final ToDoService service;

    public ToDoController(ToDoService service) {
        this.service = service;
    }

    @GetMapping
    public List<ToDo> getAllTodos() {
        return service.getAllTodos();
    }

    @GetMapping("/{id}")
    public ToDo getTodoById(@PathVariable String id) {
       return service.getTodoById(id);
    }

    @PostMapping
    public ToDo postTodo(@RequestBody ToDo newTodo) {
        return service.postTodo(newTodo);
    }

    @PutMapping("/{id}")
    public ToDo putTodo(@PathVariable String id, @RequestBody ToDo toDo) {
        return service.putTodo(id, toDo);
    }

    @DeleteMapping("/{id}")
    public void deleteTodo(@PathVariable String id) {
        service.deleteTodo(id);
    }
}
