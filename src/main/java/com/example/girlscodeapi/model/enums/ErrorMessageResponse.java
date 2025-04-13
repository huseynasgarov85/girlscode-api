package com.example.girlscodeapi.model.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public enum ErrorMessageResponse implements ResponseMessage {
    UNEXPECTED("unexpected", "unexpected error occurred", HttpStatus.INTERNAL_SERVER_ERROR),
    NOT_FOUND("not found", "data not founded", HttpStatus.NOT_FOUND),
    ;


    String key;
    String message;
    HttpStatus status;

    @Override
    public String key() {
        return key;
    }

    @Override
    public String message() {
        return message;
    }

    @Override
    public HttpStatus status() {
        return status;
    }
}
