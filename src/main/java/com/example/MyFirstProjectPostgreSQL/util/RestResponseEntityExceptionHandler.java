package com.example.MyFirstProjectPostgreSQL.util;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;

public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = {EntityNotFoundException.class})
    protected ResponseEntity<Object> handleNotFoundException(){
        return ResponseEntity.notFound().build();
    }
}
