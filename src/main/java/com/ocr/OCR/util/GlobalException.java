package com.ocr.OCR.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

@RestControllerAdvice
public class GlobalException {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleInvalidArgument(MethodArgumentNotValidException ex) {
        // Extracting the first non-null error message or providing a default message if none exists
        String message = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(FieldError::getDefaultMessage)  // Method reference for lambda
                .filter(Objects::nonNull)  // Filter out null messages
                .findFirst()
                .orElse("Invalid input");  // Handling possible null by providing a default value

        // Building and returning the API dto with the extracted message
        return ResponseHandler.generateResponse(
                message,
                HttpStatus.BAD_REQUEST,
                null
        );
    }

}
