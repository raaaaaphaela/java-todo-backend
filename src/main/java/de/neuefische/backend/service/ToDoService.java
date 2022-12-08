package de.neuefische.backend.service;

import de.neuefische.backend.model.ToDo;
import de.neuefische.backend.repository.ToDoRepository;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;

@Data
@Service
public class ToDoService {

    private final ToDoRepository repository;

    public ToDoService(ToDoRepository repository) {
        this.repository = repository;
    }

    public List<ToDo> getAllTodos() {
        return repository.getToDoList();
    }

    public ToDo getTodoById(String id) {
        return repository.getTodoById(id);
    }

    public ToDo putTodo(String id, ToDo toDo) {
        return repository.putTodo(id, toDo);
    }

    public String deleteTodo(String id) {
        return repository.deleteTodo(id);
    }

    public ToDo postTodo(ToDo newTodo) {
        return repository.postTodo(newTodo);
    }
}
