package com.example.currencygateway.controllers;

import com.example.currencygateway.dtos.CurrencyRateResponse;
import com.example.currencygateway.dtos.xml.CommandXmlRequest;
import com.example.currencygateway.dtos.xml.XmlResponse;
import com.example.currencygateway.enums.ExtServiceEnum;
import com.example.currencygateway.exceptions.DuplicateRequestException;
import com.example.currencygateway.services.CurrencyRateService;
import com.example.currencygateway.services.RequestLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/xml_api")
public class XmlApiController {

    private final CurrencyRateService currencyRateService;
    private final RequestLogService requestLogService;

    @Autowired
    public XmlApiController(CurrencyRateService currencyRateService, RequestLogService requestLogService) {
        this.currencyRateService = currencyRateService;
        this.requestLogService = requestLogService;
    }

    @PostMapping(value = "/command", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> handleXmlCommand(@RequestBody CommandXmlRequest request) throws Exception {
        if (requestLogService.isRequestDuplicated(request.getId())) {
            throw new DuplicateRequestException(request.getId());
        }
        if (request.getCurrentDetails() != null) {
            requestLogService.logRequest(request.getId(), ExtServiceEnum.EXT_SERVICE_2, request.getCurrentDetails().getConsumer());
            CurrencyRateResponse currencyRate = currencyRateService.getCurrentRateByCurrency(request.getCurrentDetails().getCurrency());
            return ResponseEntity.ok(new XmlResponse(currencyRate));
        } else if (request.getHistoryDetails() != null){
            requestLogService.logRequest(request.getId(), ExtServiceEnum.EXT_SERVICE_2, request.getHistoryDetails().getConsumer());
            List<CurrencyRateResponse> currencyRates = currencyRateService.findRatesByCurrencyAndPeriod(request.getHistoryDetails().getCurrency(),
                    request.getHistoryDetails().getPeriod());
            return ResponseEntity.ok(new XmlResponse(currencyRates));
        } else {
            return ResponseEntity.badRequest().body("No details provided in the request.");
        }
    }
}
