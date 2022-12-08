package de.neuefische.backend.service;

import de.neuefische.backend.model.ToDo;
import de.neuefische.backend.repository.ToDoRepository;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ToDoServiceTest {

    ToDoRepository repo = mock(ToDoRepository.class);
    ToDoService service = new ToDoService(repo);

    @Test
    void getAllTodos() {
        //GIVEN
        List<ToDo> list = createListWithOneElement();
        when(repo.getToDoList()).thenReturn(list);

        //WHEN
        List<ToDo> actual = service.getAllTodos();

        //THEN
        assertEquals(list, actual);
        verify(repo).getToDoList();
    }

    @Test
    void getTodoById() {
        //GIVEN
        List<ToDo> list = createListWithOneElement();
        when(repo.getTodoById("0")).thenReturn(list.get(0));

        //WHEN
        ToDo actual = service.getTodoById("0");

        //THEN
        assertEquals(list.get(0), actual);
        verify(repo).getTodoById("0");
    }

    @Test
    void putTodo() {
        // given
        ToDo putTodo = new ToDo();
        putTodo.setDescription("Todo PUT");
        putTodo.setStatus("OPEN");

        ToDo expected = new ToDo();
        expected.setId("0");
        expected.setDescription("Todo PUT");
        expected.setStatus("OPEN");

        when(repo.putTodo("0", putTodo)).thenReturn(expected);

        //WHEN
        ToDo actual = service.putTodo("0", putTodo);

        //THEN
        assertEquals(expected, actual);
        verify(repo).putTodo("0", putTodo);
    }

    @Test
    void deleteTodo() {
        // given
        String expected = "Item gelöscht.";

        when(repo.deleteTodo("0")).thenReturn(expected);

        //WHEN
        String actual = service.deleteTodo("0");

        //THEN
        assertEquals(expected, actual);
        verify(repo).deleteTodo("0");
    }

    @Test
    void deleteTodo_DoesNotExist() {
        // given
        String expected = "Kein Item mit der ID vorhanden.";

        when(repo.deleteTodo("1")).thenReturn(expected);

        //WHEN
        String actual = service.deleteTodo("1");

        //THEN
        assertEquals(expected, actual);
        verify(repo).deleteTodo("1");
    }

    @Test
    void postTodo() {
        // given
        ToDo postTodo = new ToDo();
        postTodo.setDescription("Todo POST");
        postTodo.setStatus("OPEN");

        ToDo expected = new ToDo();
        expected.setId("1");
        expected.setDescription("Todo POST");
        expected.setStatus("OPEN");

        when(repo.postTodo(postTodo)).thenReturn(expected);

        //WHEN
        ToDo actual = service.postTodo(postTodo);

        //THEN
        assertEquals(expected, actual);
        verify(repo).postTodo(postTodo);
    }

    private List<ToDo> createListWithOneElement() {
        List<ToDo> list = new ArrayList<>();
        ToDo todo = new ToDo("0", "First Todo", "OPEN");
        list.add(todo);
        return list;
    }
}