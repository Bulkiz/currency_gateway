package com.example.currencygateway.dtos.xml;


import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "error")
public class XmlErrorResponse {

    private String message;

    public XmlErrorResponse() {
    }

    public XmlErrorResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

