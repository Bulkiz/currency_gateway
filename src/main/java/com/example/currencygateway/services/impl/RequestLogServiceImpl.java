package com.example.currencygateway.services.impl;

import com.example.currencygateway.entities.RequestLog;
import com.example.currencygateway.enums.ExtServiceEnum;
import com.example.currencygateway.repositories.RequestLogRepository;
import com.example.currencygateway.services.RequestLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class RequestLogServiceImpl implements RequestLogService {

    private final RequestLogRepository requestLogRepository;

    @Autowired
    public RequestLogServiceImpl(RequestLogRepository requestLogRepository) {
        this.requestLogRepository = requestLogRepository;
    }

    @Override
    public void logRequest(String requestId, ExtServiceEnum serviceName, String clientId) {
        RequestLog requestLog = new RequestLog();
        requestLog.setRequestId(requestId);
        requestLog.setServiceName(serviceName.getValue());
        requestLog.setClientId(clientId);
        requestLog.setRequestTime(LocalDateTime.now());
        requestLogRepository.save(requestLog);
    }

    @Override
    public boolean isRequestDuplicated(String requestId) {
        return requestLogRepository.existsById(requestId);
    }
}
