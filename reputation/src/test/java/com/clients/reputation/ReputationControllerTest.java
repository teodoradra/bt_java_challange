package com.clients.reputation;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ReputationControllerTest {

    private final MockMvc mockMvc;

    @Autowired
    public ReputationControllerTest(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @Test
    public void getReputationOfClient_expectStatusOkAndValue10_whenContain12() throws Exception {
        mockMvc.perform(post("/client/reputation")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"cnp\":\"2991224301111\"}"))
                .andExpect(status().isOk());
    }

    @Test
    public void getReputationOfClient_expectStatusOkAndValue60_whenContains34() throws Exception {
        mockMvc.perform(post("/client/reputation")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"cnp\":\"2991224301134\"}"))
                .andExpect(status().isOk());
    }

    @Test
    public void getReputationOfClient_expectStatusOkAndValue100_whenElseCase() throws Exception {
        mockMvc.perform(post("/client/reputation")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"cnp\":\"2991114301111\"}"))
                .andExpect(status().isOk());
    }

}
