package com.mferpena.infrastructure.primary.controllers;

import com.mferpena.core.domain.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@ControllerAdvice
@SuppressWarnings("all")
public class RestExceptionHandler {
    @ExceptionHandler(IllegalArgumentException.class)
    protected ResponseEntity<Object> handleConflict(IllegalArgumentException ex, WebRequest request) {
        Map<String, Object> body = Map.of(
                "status", HttpStatus.NOT_FOUND.value(),
                "errors", Collections.singletonList(Map.of("message", ex.getMessage()))
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        List<Map<String, String>> errors = ex.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> Map.of(
                        "field", fieldError.getField(),
                        "message", Objects.requireNonNull(fieldError.getDefaultMessage())))
                .toList();

        Map<String, Object> body = Map.of(
                "status", HttpStatus.BAD_REQUEST.value(),
                "errors", errors);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<Object> handleOrderNotFoundException(OrderNotFoundException ex) {
        ResponseStatus statusAnnotation = ex.getClass().getAnnotation(ResponseStatus.class);

        HttpStatus status = statusAnnotation != null ? statusAnnotation.value() : HttpStatus.INTERNAL_SERVER_ERROR;

        Map<String, Object> body = Map.of(
                "status", status.value(),
                "errors", Collections.singletonList(ex.getMessage()));

        return ResponseEntity.status(status).body(body);
    }

    @ExceptionHandler(ProductNotFoundInOrderException.class)
    public ResponseEntity<Object> handleProductNotFoundInOrderException(ProductNotFoundInOrderException ex) {
        ResponseStatus statusAnnotation = ex.getClass().getAnnotation(ResponseStatus.class);

        HttpStatus status = statusAnnotation != null ? statusAnnotation.value() : HttpStatus.INTERNAL_SERVER_ERROR;

        Map<String, Object> body = Map.of(
                "status", status.value(),
                "errors", Collections.singletonList(ex.getMessage()));

        return ResponseEntity.status(status).body(body);
    }

    @ExceptionHandler(InvalidQuantityException.class)
    public ResponseEntity<Object> handleInvalidQuantityException(InvalidQuantityException ex) {
        ResponseStatus statusAnnotation = ex.getClass().getAnnotation(ResponseStatus.class);

        HttpStatus status = statusAnnotation != null ? statusAnnotation.value() : HttpStatus.INTERNAL_SERVER_ERROR;

        Map<String, Object> body = Map.of(
                "status", status.value(),
                "errors", Collections.singletonList(ex.getMessage()));

        return ResponseEntity.status(status).body(body);
    }

    @ExceptionHandler(ExceededQuantityException.class)
    public ResponseEntity<Object> handleExceededQuantityException(ExceededQuantityException ex) {
        ResponseStatus statusAnnotation = ex.getClass().getAnnotation(ResponseStatus.class);

        HttpStatus status = statusAnnotation != null ? statusAnnotation.value() : HttpStatus.INTERNAL_SERVER_ERROR;

        Map<String, Object> body = Map.of(
                "status", status.value(),
                "errors", Collections.singletonList(ex.getMessage()));

        return ResponseEntity.status(status).body(body);
    }

    @ExceptionHandler(UpdateFailedException.class)
    public ResponseEntity<Object> handleUpdateFailedException(UpdateFailedException ex) {
        ResponseStatus statusAnnotation = ex.getClass().getAnnotation(ResponseStatus.class);

        HttpStatus status = statusAnnotation != null ? statusAnnotation.value() : HttpStatus.INTERNAL_SERVER_ERROR;

        Map<String, Object> body = Map.of(
                "status", status.value(),
                "errors", Collections.singletonList(ex.getMessage()));

        return ResponseEntity.status(status).body(body);
    }
}
