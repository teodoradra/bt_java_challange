package com.clients.enrolment.utils;

import lombok.Getter;

public enum ClientRisk {
    NO_RISK ("Candidate with no risk"),
    MEDIUM_RISK ("Candidate with medium risk, but enrollment still possible"),
    HIGH_RISK ("Risky candidate, enrollment not acceptable");

    @Getter
    private final String message;
    ClientRisk(String message) {
        this.message = message;
    }

    public static ClientRisk getRisk(int riskValue) {
        if (riskValue <= 20) return NO_RISK;
        if (riskValue <= 99) return MEDIUM_RISK;
        return HIGH_RISK;
    }
}
