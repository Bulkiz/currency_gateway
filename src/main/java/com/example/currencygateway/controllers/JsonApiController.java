package com.example.currencygateway.controllers;

import com.example.currencygateway.dtos.CurrencyRateHistoryRequest;
import com.example.currencygateway.dtos.CurrencyRateRequest;
import com.example.currencygateway.dtos.CurrencyRateResponse;
import com.example.currencygateway.enums.ExtServiceEnum;
import com.example.currencygateway.exceptions.DuplicateRequestException;
import com.example.currencygateway.services.CurrencyRateService;
import com.example.currencygateway.services.RequestLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/json_api")
public class JsonApiController {

    private final CurrencyRateService currencyRateService;
    private final RequestLogService requestLogService;

    @Autowired
    public JsonApiController(CurrencyRateService currencyRateService, RequestLogService requestLogService) {
        this.currencyRateService = currencyRateService;
        this.requestLogService = requestLogService;
    }

    @PostMapping("/current")
    public ResponseEntity<CurrencyRateResponse> getCurrentCurrencyRate(@RequestBody CurrencyRateRequest request) throws Exception {
        if (requestLogService.isRequestDuplicated(request.getRequestId())) {
            throw new DuplicateRequestException(request.getRequestId());
        }
        requestLogService.logRequest(request.getRequestId(), ExtServiceEnum.EXT_SERVICE_1, request.getClient());
        return ResponseEntity.ok(currencyRateService.getCurrentRateByCurrency(request.getCurrency()));
    }

    @PostMapping("/history")
    public ResponseEntity<List<CurrencyRateResponse>> getCurrencyRatesForPeriod(@RequestBody CurrencyRateHistoryRequest request) throws Exception {
        if (requestLogService.isRequestDuplicated(request.getRequestId())) {
            throw new DuplicateRequestException(request.getRequestId());
        }
        requestLogService.logRequest(request.getRequestId(), ExtServiceEnum.EXT_SERVICE_1, request.getClient());
        return ResponseEntity.ok(currencyRateService.findRatesByCurrencyAndPeriod(request.getCurrency(), request.getPeriod()));
    }
}
