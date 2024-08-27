package com.example.currencygateway.services;

import com.example.currencygateway.enums.ExtServiceEnum;

public interface RequestLogService {

    void logRequest(String requestId, ExtServiceEnum serviceName, String clientId);

    boolean isRequestDuplicated(String requestId);

}
