@XmlJavaTypeAdapters({
        @XmlJavaTypeAdapter(value=LocalDateTimeXmlAdapter.class, type=LocalDateTime.class)
})
package com.example.currencygateway.dtos;

import com.example.currencygateway.utils.LocalDateTimeXmlAdapter;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapters;

import java.time.LocalDateTime;