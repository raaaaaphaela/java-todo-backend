package de.neuefische.backend.repository;

import de.neuefische.backend.model.ToDo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

class ToDoRepositoryTest {

    @Test
    void getToDoList_shouldReturnFullList() {
        // given
        List<ToDo> list = createListWithOneElement();
        ToDoRepository repository = new ToDoRepository(list);

        // when
        List<ToDo> actual = repository.getToDoList();

        // then
        Assertions.assertEquals(list, actual);
    }

    @Test
    void getTodoById_whenValidId_shouldReturnTodo() {
        // given
        List<ToDo> list = createListWithOneElement();
        ToDoRepository repository = new ToDoRepository(list);

        // when
        ToDo actual = repository.getTodoById("0");

        // then
        Assertions.assertEquals(list.get(0), actual);
    }

    @Test
    void getTodoById_WhenInvalidId_ThenExpectException() {
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
    void putTodo_WhenValidId_ThenUpdateExistingTodo() {
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
    void deleteTodo_WhenExistingToDo_ThenDelete() {

        // given
        List<ToDo> list = createListWithOneElement();
        ToDoRepository repository = new ToDoRepository(list);

        // when
        repository.deleteTodo("0");

        // then
        Assertions.assertEquals(0, list.size());
    }

    @Test
    void deleteTodo_WhenNonExistentToDo_ShouldReturnString() {

        // given
        List<ToDo> list = createListWithOneElement();
        ToDoRepository repository = new ToDoRepository(list);

        String expected = "Kein Item mit der ID vorhanden.";

        // when
        String actual = repository.deleteTodo("1");

        // then
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void postTodo_whenValidTodo_ThenSaveTodo() {
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

    private List<ToDo> createListWithOneElement() {
        List<ToDo> list = new ArrayList<>();
        ToDo todo = new ToDo("0", "First Todo", "OPEN");
        list.add(todo);
        return list;
    }
}