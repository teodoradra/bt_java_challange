package com.clients.existence.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClientCheckRequest {
    private String documentId;
    private String firstName;
    private String lastName;
    private String cnp;
    private String documentExpDate;
}
