package com.api.desafio.enums;

public enum Status {

    ENVIADO("Enviado"),
    NAO_ENVIADO("Não enviado");

    private final String value;

    Status(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
