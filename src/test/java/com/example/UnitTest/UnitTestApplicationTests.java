package com.example.UnitTest;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class UnitTestApplicationTests {

    @Autowired
    private MockMvc mockMvc;


    @Test
    void contextLoads() {
    }

    @Test
    void shouldCreateUser() throws Exception {
        this.mockMvc.perform(
                        post("/user")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\"name\":\"Fabrizio\",\"surname\":\"Lagioia\"}\"")
                )
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":1,\"name\":\"Fabrizio\",\"surname\":\"Lagioia\"}"));
    }

    @Test
    void shouldReturnVoid() throws Exception {
        this.mockMvc.perform(
                        post("/user"))
                .andDo(print())
                .andExpect(status().isBadRequest()
                );
    }

    private void createUserRequest() throws Exception {
        mockMvc.perform(post("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                "name": "Fabrizio",
                                "surname": "Lagioia"
                                }
                                """))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void shouldGetUser() throws Exception {
        createUserRequest();
        mockMvc.perform(
                        get("/user?id=1")
                )
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        {
                        "id": 1,
                        "name": "Fabrizio",
                        "surname": "Lagioia"
                         }"""));
    }

    @Test
    void shouldGetUser1() throws Exception {
        createUserRequest();
        mockMvc.perform(
                        get("/user?id=1")
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        {
                        "id": 1,
                        "name": "Fabrizio",
                        "surname": "Lagioia"
                         }"""));
    }

    @Test
    void shouldUpdateUser() throws Exception {
        createUserRequest();
        mockMvc.perform(
                        put("/user/1?name=Lorenzo&surname=Catania")
                )
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        {
                        "id": 1,
                        "name": "Lorenzo",
                        "surname": "Catania"
                        }
                        """));
    }

    @Test
    void shouldDeleteUser() throws Exception {
        createUserRequest();
        mockMvc.perform(
                delete("/user/delete1")
        )
                .andExpect(status().isOk());
    }
}
