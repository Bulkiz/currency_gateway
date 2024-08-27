package com.example.currencygateway.enums;

public enum ExtServiceEnum {
    EXT_SERVICE_1("EXT_SERVICE_1"), EXT_SERVICE_2("EXT_SERVICE_2");

    private final String value;

    ExtServiceEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
