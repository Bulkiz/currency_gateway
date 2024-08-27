package com.example.currencygateway.exceptions;

public class DuplicateRequestException extends Exception {
    public DuplicateRequestException(String requestId) {
        super("Request with id " + requestId + " was already send!");
    }
}
