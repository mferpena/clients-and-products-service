package com.mferpena.core.domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ExceededQuantityException extends RuntimeException {
    public ExceededQuantityException(String message) {
        super(message);
    }
}
