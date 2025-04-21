package com.example.girlscodeapi.model.enums.exception;

import org.springframework.http.HttpStatus;

public interface ResponseMessage {
    String key();

    String message();

    HttpStatus status();
}
