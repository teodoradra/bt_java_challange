package com.clients.enrolment.dto;

import lombok.Getter;

@Getter
public enum Document {
    ENROLMENT("enrolment.txt"),
    DENIAL("denial.txt");

    private String file;

    Document(String file) {
        this.file = file;
    }
}
