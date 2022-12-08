package de.neuefische.backend.repository;

import de.neuefische.backend.model.ToDo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ToDoRepositoryTest {

    private List<ToDo> createListWithOneElement() {
        List<ToDo> list = new ArrayList<>();
        ToDo todo = new ToDo("0", "First Todo", "OPEN");
        list.add(todo);
        return list;
    }

    @Test
    void getTodoById() {
        // given
        List<ToDo> list = createListWithOneElement();

        ToDoRepository repository = new ToDoRepository(list);

        // when
        ToDo actual = repository.getTodoById("0");

        // then
        Assertions.assertEquals(list.get(0), actual);
    }

    @Test
    void getTodoByInvalidId_expectException() {
        // given
        List<ToDo> list = createListWithOneElement();

        ToDoRepository repository = new ToDoRepository(list);

        try {
        repository.getTodoById("1");
        fail();

        } catch (IndexOutOfBoundsException e) {
            assertThat(e)
                    .isInstanceOf(IndexOutOfBoundsException.class)
                    .hasMessage("Index 1 out of bounds for length 1");
        }
    }

    @Test
    void putTodo() {
        // given
        List<ToDo> list = createListWithOneElement();

        ToDo changedTodo = new ToDo("0", "FirstTodo", "OPEN");

        ToDoRepository repository = new ToDoRepository(list);

        // when
        ToDo actual = repository.putTodo("0", changedTodo);

        // then
        Assertions.assertEquals(changedTodo, actual);
    }

    @Test
    void deleteTodo() {

        // given
        List<ToDo> list = createListWithOneElement();
        ToDoRepository repository = new ToDoRepository(list);

        // when
        repository.deleteTodo("0");

        // then
        Assertions.assertEquals(0, list.size());
    }

    @Test
    void postTodo() {
        // given
        List<ToDo> list = createListWithOneElement();
        ToDo newTodo = new ToDo();
        newTodo.setDescription("Zweites Todo");
        newTodo.setStatus("OPEN");

        ToDoRepository repository = new ToDoRepository(list);

        // when
        ToDo actual = repository.postTodo(newTodo);

        // then

        Assertions.assertEquals(new ToDo("1", "Zweites Todo", "OPEN"), actual);
    }

    @Test
    void getToDoList() {
        // given
        List<ToDo> list = createListWithOneElement();
        ToDoRepository repository = new ToDoRepository(list);

        // when
        List<ToDo> actual = repository.getToDoList();

        // then
        Assertions.assertEquals(list, actual);
    }
}