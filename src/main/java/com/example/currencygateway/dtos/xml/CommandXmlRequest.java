package com.example.currencygateway.dtos.xml;

import jakarta.xml.bind.annotation.*;

@XmlRootElement(name = "command")
public class CommandXmlRequest {

    private String id;

    private XmlCurrentDetails currentDetails;

    private XmlHistoryDetails historyDetails;

    @XmlAttribute(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @XmlElement(name = "get")
    public XmlCurrentDetails getCurrentDetails() {
        return currentDetails;
    }

    public void setCurrentDetails(XmlCurrentDetails currentDetails) {
        this.currentDetails = currentDetails;
    }

    @XmlElement(name = "history")
    public XmlHistoryDetails getHistoryDetails() {
        return historyDetails;
    }

    public void setHistoryDetails(XmlHistoryDetails historyDetails) {
        this.historyDetails = historyDetails;
    }
}
