package com.codesoom.myseat.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@WebMvcTest(RetrospectiveController.class)
class RetrospectiveControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    void retrospective_save() throws Exception {
        String content = "\"retrospective\" : \"잘했다.\"";
        Long id = 1L;
        mockMvc.perform(post("/reservations/{id}/retrospectives", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content)
                )
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

}
