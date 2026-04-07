package com.resto.pizzeria_api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ApiNotFoundException extends Exception {
    public ApiNotFoundException(String message) {
        super(message);
    }
}
