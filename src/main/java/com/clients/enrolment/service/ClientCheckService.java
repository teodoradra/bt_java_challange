package com.clients.enrolment.service;

import com.clients.enrolment.api.ExistenceApi;
import com.clients.enrolment.api.ReputationApi;
import com.clients.enrolment.dto.ClientCheckRequest;
import com.clients.enrolment.dto.ClientCheckResponse;
import com.clients.enrolment.dto.Document;
import com.clients.enrolment.utils.ClientRisk;
import com.clients.enrolment.utils.ConnectionToApiException;
import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.net.ConnectException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

@Service
@AllArgsConstructor
public class ClientCheckService {

//    Logger logger = LoggerFactory.getLogger(ClientCheckService.class);

    private final ExistenceApi existenceApi;
    private final ReputationApi reputationApi;

    public ClientCheckResponse performClientCheck(ClientCheckRequest request) {
        // Perform document validity check
        boolean validDocument = checkDocumentValidity(request.getDocumentExpDate());

        // Query external system for reputation score
        int reputationScore = reputationApi.getClientReputation(request);

        // Query external system for client existence
        boolean clientExists = existenceApi.checkClientExistence(request);

        return new ClientCheckResponse(validDocument, ClientRisk.getRisk(reputationScore).getMessage(), clientExists);
    }

    private Boolean getClientExistence(ClientCheckRequest clientCheckRequest) {
        try {
            return existenceApi.checkClientExistence(clientCheckRequest);
        } catch (Exception e) {
            throw new ConnectionToApiException("Connection refused for existence api");
        }
    }

    private Integer getClientReputation(ClientCheckRequest clientCheckRequest) {
        try {
            return reputationApi.getClientReputation(clientCheckRequest);
        } catch (Exception e) {
            throw new ConnectionToApiException("Connection refused for reputation api");
        }
    }

    private boolean checkDocumentValidity(String expirationDateDocument) {
        // Implementation to check document validity (e.g., expiry)
        // Return true/false based on validity
        // Placeholder implementation
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date documentDate = formatter.parse(expirationDateDocument);
            Date currentDate = new Date(); // Get current date

            // Example: Check if documentDate is not expired (before current date)
            return !documentDate.before(currentDate);
        } catch (NullPointerException | ParseException e) {
            // Handle parsing exceptions
//            logger.info("Error raised while parsing data");
            return false; // Return false for invalid date format
        }
    }

    public byte[] getDocument(Document documentType) {
        String pathToDoc = "/document_template/" + documentType.getFile();
        Path path = null;
        try {
            path = Paths.get(Objects.requireNonNull(getClass().getResource(pathToDoc)).toURI());
            return new ByteArrayResource(Files.readAllBytes(path)).getContentAsByteArray();
        } catch (URISyntaxException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
