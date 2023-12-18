package com.clients.existence.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClientCheckResponse {
    private boolean validDocument;
    private String reputationScore;
    private boolean clientExists;

    // Other fields as needed
    // Getters, setters, constructors
}
