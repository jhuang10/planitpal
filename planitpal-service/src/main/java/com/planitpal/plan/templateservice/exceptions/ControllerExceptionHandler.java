package com.planitpal.plan.templateservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ResponseBody
    @ExceptionHandler(ExampleNotFoundException.class)
    public ResponseEntity handle(ExampleNotFoundException exception) {
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

}
