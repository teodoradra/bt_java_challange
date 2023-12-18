package com.clients.existence;

import com.clients.existence.entity.Client;
import com.clients.existence.repository.ClientRepository;
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
public class ExistenceControllerTest {
    private final MockMvc mockMvc;
    private final ClientRepository clientRepository;

    @Autowired
    public ExistenceControllerTest(MockMvc mockMvc, ClientRepository clientRepository) {
        this.mockMvc = mockMvc;
        this.clientRepository = clientRepository;
        addClientDataTest();
    }

    public void addClientDataTest() {
        clientRepository.save(new Client("2991114301111", "First Name", "Last Name", "111111", "2027-12-24"));
    }

    @Test
    void checkClientExistence_ShouldReturnTrue_WhenClientExists() throws Exception {
        String jsonInput = "{" +
                "\"documentId\": \"1111111111111\"," +
                "\"documentExpDate\" : \"2027-12-01\"," +
                "\"cnp\" : \"2991114301111\"," +
                "\"firstName\" : \"Teodora\"," +
                "\"lastName\" : \"Dragsuin\"}";
        mockMvc.perform(post("/client/existence")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonInput))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }

    @Test
    void checkClientExistence_ShouldReturnFalse_WhenClientDoesNotExist() throws Exception {
        String jsonInput = "{" +
                "\"documentId\": \"1111111111111\"," +
                "\"documentExpDate\" : \"2027-12-01\"," +
                "\"cnp\" : \"2991114301112\"," +
                "\"firstName\" : \"Teodora\"," +
                "\"lastName\" : \"Dragsuin\"}";
        mockMvc.perform(post("/client/existence")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonInput))
                .andExpect(status().isOk())
                .andExpect(content().string("false"));
    }
}
