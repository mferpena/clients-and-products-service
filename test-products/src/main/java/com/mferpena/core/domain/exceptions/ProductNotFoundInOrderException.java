package com.mferpena.core.domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProductNotFoundInOrderException extends RuntimeException {
    public ProductNotFoundInOrderException(String message) {
        super(message);
    }
}
