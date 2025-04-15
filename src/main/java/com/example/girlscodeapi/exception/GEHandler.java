package com.example.girlscodeapi.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;

@RestControllerAdvice
@Slf4j
public class GEHandler {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionDto handleNotFound(NotFoundException ex){
        log.error("Action.error.log -> NOT_FOUND: {}",ex.getMessage());
        return ExceptionDto.builder().message(ex.getMessage()).code(HttpStatus.NOT_FOUND.value()).build();
    }
    @ExceptionHandler(AlreadyExistException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ExceptionDto handleAlreadyExist(AlreadyExistException ex){
        log.error("Action.error.log -> ALREADY_EXIST: {}",ex.getMessage());
        return ExceptionDto.builder().message(ex.getMessage()).code(HttpStatus.CONFLICT.value()).build();
    }

    @ExceptionHandler(IOException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionDto handleIOException(IOException ex){
        log.error("Action.error.log -> INTERNAL_SERVER: {}",ex.getMessage());
        return ExceptionDto.builder().message(ex.getMessage()).code(HttpStatus.INTERNAL_SERVER_ERROR.value()).build();
    }
}
