package com.example.currencygateway.mappers;

import com.example.currencygateway.dtos.CurrencyRateResponse;
import com.example.currencygateway.entities.CurrencyRate;
import org.springframework.stereotype.Component;

@Component
public class CurrencyRateMapper {

    public CurrencyRateResponse mapToResponse(CurrencyRate currencyRate) {
        CurrencyRateResponse currencyRateResponse = new CurrencyRateResponse();
        currencyRateResponse.setCurrency(currencyRate.getCurrency());
        currencyRateResponse.setRate(currencyRate.getRate());
        currencyRateResponse.setTimestamp(currencyRate.getTimestamp());
        return currencyRateResponse;
    }
}
