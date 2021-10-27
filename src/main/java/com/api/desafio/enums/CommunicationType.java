package com.api.desafio.enums;

public enum CommunicationType {

    EMAIL("Email"),
    SMS("SMS"),
    PUSH("PUSH"),
    WHATS("WHATS");

    private final String value;

    CommunicationType(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
