package com.clients.enrolment.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClientCheckRequest implements Serializable {
    @NotNull(message = "Document ID cannot be null")
    @NotBlank(message = "Document ID cannot be blank")
    private String documentId;

    @NotNull(message = "First name cannot be null")
    @NotBlank(message = "First name cannot be blank")
    private String firstName;

    @NotNull(message = "Last name cannot be null")
    @NotBlank(message = "Last name cannot be blank")
    private String lastName;

    @NotNull(message = "Personal number cannot be null")
    @Size(max = 13, min = 13, message = "Personal number must be composed of 13 digits")
    @Pattern(regexp = "[0-9]+", message = "Personal number should have only digits")
    private String cnp;

    @NotNull(message = "Expiration date cannot be null!")
    private String documentExpDate;
}
