package com.example.currencygateway.dtos.xml;

import jakarta.xml.bind.annotation.XmlAttribute;

public class XmlHistoryDetails {

    private String consumer;
    private String currency;
    private Integer period;

    @XmlAttribute(name = "consumer")
    public String getConsumer() {
        return consumer;
    }

    public void setConsumer(String consumer) {
        this.consumer = consumer;
    }

    @XmlAttribute(name = "currency")
    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @XmlAttribute(name = "period")
    public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }
}
