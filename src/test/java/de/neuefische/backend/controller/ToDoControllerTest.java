package de.neuefische.backend.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
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
    void getAllTodos_InitialState_ShouldReturnEmptyList() throws Exception {
        // given
        String expectedJSON = "[]";

        // when & then
        mvc.perform(MockMvcRequestBuilders.get("/api/todo"))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJSON, true));
    }

    @Test
    void postTodo_ShouldReturnOneTodo() throws Exception {
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

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    void putTodo_ShouldReturnOneUpdatedTodo() throws Exception {
        // given
        String initial = """
                {
                "description": "Neu",
                "status": "OPEN"
                }
                """;

        String saved = """
                {
                "id" : "0",
                "description": "Neu",
                "status": "OPEN"
                }
                """;

        String updated = """
                {
                "id" : "0",
                "description": "Todo: Changed",
                "status": "OPEN"
                }
                """;

        mvc.perform(MockMvcRequestBuilders.post("/api/todo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(initial))
                .andExpect(status().isOk())
                .andExpect(content().json(saved, true));

        mvc.perform(MockMvcRequestBuilders.put("/api/todo/0")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updated))
                .andExpect(status().isOk())
                .andExpect(content().json(updated, true));
    }

    @Test
    void deleteTodo_ShouldReturn200Response() throws Exception {
        // given
        String initial = """
                {
                "description": "Neu",
                "status": "OPEN"
                }
                """;

        String saved = """
                {
                "id" : "0",
                "description": "Neu",
                "status": "OPEN"
                }
                """;

        mvc.perform(MockMvcRequestBuilders.post("/api/todo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(initial))
                .andExpect(status().isOk())
                .andExpect(content().json(saved, true));

        mvc.perform(MockMvcRequestBuilders.delete("/api/todo/0"))
                .andExpect(status().isOk());
    }
}