package com.uca.parcialfinalncapas.utils.enums;

import lombok.Getter;

@Getter
public enum Rol {
    USER("ROLE_USER"),
    TECH("ROLE_TECH");

    private final String value;

    Rol(String value) {
        this.value = value;
    }
}
