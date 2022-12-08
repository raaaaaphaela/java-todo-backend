package de.neuefische.backend.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ToDoControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void getAllTodos_expectEmptyList() throws Exception {
        // given
        String expectedJSON = "[]";

        // when & then
        mvc.perform(MockMvcRequestBuilders.get("/api/todo"))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJSON, true));
    }

    @Test
    void postOneTodos_ShouldReturnOneTodo() throws Exception {
        // given
        String JSON = """
                {
                "id" : "0",
                "description": "Neu",
                "status": "OPEN"
                }
                """;

        mvc.perform(MockMvcRequestBuilders.post("/api/todo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(JSON, true));

        // when & then
        mvc.perform(MockMvcRequestBuilders.get("/api/todo/0"))
                .andExpect(status().isOk())
                .andExpect(content().json(JSON, true));
    }
}