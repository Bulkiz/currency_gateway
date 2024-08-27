package com.example.currencygateway.controllers.advice;

import com.example.currencygateway.dtos.xml.XmlErrorResponse;
import com.example.currencygateway.exceptions.DuplicateRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class AppExceptionHandler {

//    @ExceptionHandler(Exception.class) //catching all other exceptions, so we don't expose the stacktrace
//    public ResponseEntity<String> handleInternalServerError(Exception e) {
//        System.out.println(e.getMessage()); // simulating log
//        return ResponseEntity.internalServerError().body("There was a problem with the server");
//    }

    @ExceptionHandler(DuplicateRequestException.class)
    public ResponseEntity<?> handleDuplicateRequest(DuplicateRequestException e, WebRequest request) {
        if (MediaType.APPLICATION_XML_VALUE.equals(request.getHeader("Content-Type"))) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.APPLICATION_XML).body(new XmlErrorResponse(e.getMessage()));
        } else if (MediaType.APPLICATION_JSON_VALUE.equals(request.getHeader("Content-Type"))){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.APPLICATION_JSON).body(e.getMessage());
        } else {
            return ResponseEntity.badRequest().body("Unhandled request");
        }
    }
}
