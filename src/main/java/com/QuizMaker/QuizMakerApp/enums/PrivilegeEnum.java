package com.QuizMaker.QuizMakerApp.enums;

import lombok.Getter;

@Getter
public enum PrivilegeEnum {
    ROLE_MANIPULATION("role_manipulation"),
    PRIVILEGE_MANIPULATION("privilege_manipulation");

    private String value;
    PrivilegeEnum(String value) {
        this.value = value;
    }
}
