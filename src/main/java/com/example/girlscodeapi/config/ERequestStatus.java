package com.example.girlscodeapi.config;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.http.HttpStatus;

import java.util.Arrays;

public enum ERequestStatus {
    PENDING,
    APPROVED,
    DECLINED,
    ;

    @JsonValue
    public static String toValue(ERequestStatus status) {
        return status.name();
    }

    @JsonCreator
    public static ERequestStatus fromValue(String status) {
        return Arrays
                .stream(ERequestStatus.values())
                .filter(e -> e
                        .name()
                        .equalsIgnoreCase(status))
                .findFirst()
                .orElseThrow(null);
    }

    public String fromName() {
        return name().toUpperCase();
    }

    @JsonValue
    public String getDisplayName() {
        return name().toUpperCase();
    }
}