package com.example.currencygateway.dtos;

import java.time.LocalDateTime;


public class CurrencyRateResponse {

    private String currency;

    private Double rate;

    private LocalDateTime timestamp;

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
