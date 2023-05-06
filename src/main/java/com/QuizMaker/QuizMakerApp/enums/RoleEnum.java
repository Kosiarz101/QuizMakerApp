package com.QuizMaker.QuizMakerApp.enums;

import lombok.Getter;

@Getter
public enum RoleEnum {
    ADMIN("admin"),
    USER("user");

    private String value;
    RoleEnum(String value) {
        this.value = value;
    }
}
