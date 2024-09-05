package com.ocr.OCR.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.LinkedHashMap;
import java.util.Map;

public class ResponseHandler {
    public static ResponseEntity<Object> generateResponse(String message, HttpStatus status, Object responseObj) {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("status", status);
        map.put("statusCode", status.value());
        map.put("message", message);
        map.put("data", responseObj);

        return new ResponseEntity<>(map, status);
    }
}
