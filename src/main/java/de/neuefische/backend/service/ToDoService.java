package de.neuefische.backend.service;

import de.neuefische.backend.model.ToDo;
import de.neuefische.backend.repository.ToDoRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@NoArgsConstructor
public class ToDoService {

    private ToDoRepository repository;

    @Autowired
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

    public void deleteTodo(String id) {
        repository.deleteTodo(id);
    }

    public ToDo postTodo(ToDo newTodo) {
        return repository.postTodo(newTodo);
    }
}
