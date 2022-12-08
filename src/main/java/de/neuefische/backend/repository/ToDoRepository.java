package de.neuefische.backend.repository;

import de.neuefische.backend.model.ToDo;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Data
@Component
@AllArgsConstructor
public class ToDoRepository {

    private List<ToDo> toDoList = new ArrayList<>();

    public ToDoRepository() {
        ToDo toDo1 = new ToDo("0", "First Todo", "OPEN");
        toDoList.add(toDo1);
    }

    public ToDo getTodoById(String id) {
        return toDoList.get(Integer.parseInt(id));
    }

    public ToDo putTodo(String id, ToDo toDo) {

        if (toDo != null && toDo.getDescription() != null) {
            toDoList.get(Integer.parseInt(id)).setDescription(toDo.getDescription());
            toDoList.get(Integer.parseInt(id)).setStatus(toDo.getStatus());
        }
        return toDoList.get(Integer.parseInt(id));
    }

    public void deleteTodo(String id) {
        toDoList.remove(toDoList.get(Integer.parseInt(id)));
    }

    public ToDo postTodo(ToDo newTodo) {
        newTodo.setId(String.valueOf(toDoList.size()));
        toDoList.add(newTodo);
        return newTodo;
    }
}
