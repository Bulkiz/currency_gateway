package com.example.currencygateway.services;

import com.example.currencygateway.dtos.CurrencyRateResponse;

import java.util.List;

public interface CurrencyRateService {
    CurrencyRateResponse getCurrentRateByCurrency(String currency);

    List<CurrencyRateResponse> findRatesByCurrencyAndPeriod(String currency, Integer period);
}
