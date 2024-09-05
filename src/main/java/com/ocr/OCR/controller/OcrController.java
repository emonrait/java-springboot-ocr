package com.ocr.OCR.controller;

import com.ocr.OCR.util.ResponseHandler;
import com.ocr.OCR.service.TesseractOCRService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
public class OcrController {

    private final TesseractOCRService tesseractOCRService;

    @PostMapping("/ocr")
    public ResponseEntity<Object> recognizeText(@RequestParam("file") MultipartFile file) {
        try {
            String value = tesseractOCRService.recognizeText(file.getInputStream());

            return ResponseHandler.generateResponse("Request Process Successful.", HttpStatus.OK, value);

        } catch (Exception ex) {
            return ResponseHandler.generateResponse(ex.getMessage(), HttpStatus.BAD_REQUEST, null);
        }
    }

}
