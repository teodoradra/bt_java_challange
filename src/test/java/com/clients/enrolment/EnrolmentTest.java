package com.clients.enrolment;

import com.clients.enrolment.api.ExistenceApi;
import com.clients.enrolment.api.ReputationApi;
import com.clients.enrolment.utils.ClientRisk;
import com.clients.enrolment.utils.ConnectionToApiException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;

import java.net.ConnectException;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class EnrolmentTest {
    private final MockMvc mockMvc;

    @MockBean
    private ReputationApi reputationApi;

    @MockBean
    private ExistenceApi existenceApi;

    @Autowired
    public EnrolmentTest(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @Test
    public void enrolmentCheck_shouldReturnOk_whenAllFieldsArePresentAndValidAndApisAreWorking() throws Exception {
        Mockito.when(reputationApi.getClientReputation(any())).thenReturn(100);
        Mockito.when(existenceApi.checkClientExistence(any())).thenReturn(false);

        String jsonInput = "{" +
                "\"documentId\": \"1111111111111\"," +
                "\"documentExpDate\" : \"2027-12-01\"," +
                "\"cnp\" : \"1111111111111\"," +
                "\"firstName\" : \"Teodora\"," +
                "\"lastName\" : \"Dragsuin\"}";

        mockMvc.perform(post("/enrolment/client-check")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonInput))
                .andExpect(status().isOk())
                .andExpect(jsonPath("validDocument", is(true)))
                .andExpect(jsonPath("reputationScore", is(ClientRisk.HIGH_RISK.getMessage())))
                .andExpect(jsonPath("clientExists", is(false)));
    }

//    @Test
//    public void enrolmentCheck_shouldReturn400_whenDocumentIdMissing() throws Exception {
//        Mockito.when(reputationApi.getClientReputation(any())).thenReturn(100);
//        Mockito.when(existenceApi.checkClientExistence(any())).thenReturn(false);
//
//        String jsonInput = "{"+"}";
//
//        mockMvc.perform(post("/enrolment/client-check")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(jsonInput))
//                .andExpect(status().isBadRequest())
//                .andExpect(content().string("Expiration date cannot be null"));
//    }
//
//    @Test
//    public void enrolmentCheck_shouldReturn400_whenReputationCallNotWorking() throws Exception {
//        Mockito.when(existenceApi.checkClientExistence(any())).thenReturn(false);
//        Mockito.when(reputationApi.getClientReputation(any())).thenThrow(ConnectionToApiException.class);
//
//        String jsonInput = "{" +
//                "\"documentId\": \"1111111111111\"," +
//                "\"documentExpDate\" : \"2027-12-01\"," +
//                "\"cnp\" : \"1111111111111\"," +
//                "\"firstName\" : \"Teodora\"," +
//                "\"lastName\" : \"Dragsuin\"}";
//
//        mockMvc.perform(post("/enrolment/client-check")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(jsonInput))
//                .andExpect(status().isInternalServerError())
//                .andExpect(content().string("Connection refused for existence api"));
//    }

    @Test
    public void generateDocument_shouldReturnOk_whenDocumentTypeENROLMENT() throws Exception {
        mockMvc.perform(post("/enrolment/generate?type=ENROLMENT")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isOk());
    }

    @Test
    public void generateDocument_shouldReturnOk_whenDocumentTypeDENIAL() throws Exception {
        mockMvc.perform(post("/enrolment/generate?type=DENIAL")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isOk());
    }

    @Test
    public void generateDocument_shouldReturnOk_whenDocumentTypeInvalid() throws Exception {
        mockMvc.perform(post("/enrolment/generate?type=invalid")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void generateDocument_shouldReturnOk_whenSubmitValidFile() throws Exception {
        MockMultipartFile file = new MockMultipartFile("file", "test.txt",
                MediaType.TEXT_PLAIN_VALUE, "Test".getBytes());
        mockMvc.perform(multipart("/enrolment/submit").file(file))
                .andExpect(status().isOk());
    }
}
