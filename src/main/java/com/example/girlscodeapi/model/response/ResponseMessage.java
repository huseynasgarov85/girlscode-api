package com.example.girlscodeapi.model.response;

import org.springframework.http.HttpStatus;

public interface ResponseMessage {
    String key();

    String message();

    HttpStatus status();
}
