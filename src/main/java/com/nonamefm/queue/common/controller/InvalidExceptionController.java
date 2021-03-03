package com.nonamefm.queue.common.controller;

import com.nonamefm.queue.common.exception.InvalidArgumentException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class InvalidExceptionController {

    @ExceptionHandler(InvalidArgumentException.class)
    public ResponseEntity<String> badRequestHandler(Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
