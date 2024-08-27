package com.example.currencygateway.dtos.xml;

import com.example.currencygateway.dtos.CurrencyRateResponse;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.Collections;
import java.util.List;

@XmlRootElement(name = "response")
public class XmlResponse {

    private List<CurrencyRateResponse> rates;

    public XmlResponse() {
    }

    public XmlResponse(CurrencyRateResponse rate) {
        this.rates = Collections.singletonList(rate);

    }

    public XmlResponse(List<CurrencyRateResponse> rates) {
        this.rates = rates;
    }

    public List<CurrencyRateResponse> getRates() {
        return rates;
    }

    public void setRates(List<CurrencyRateResponse> rates) {
        this.rates = rates;
    }
}

