package com.example.currencygateway.services.scheduled;

import com.example.currencygateway.dtos.FixerIoResponse;
import com.example.currencygateway.entities.CurrencyRate;
import com.example.currencygateway.repositories.CurrencyRateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

@Service
public class FixerIoService {

    private final CurrencyRateRepository currencyRateRepository;

    private final RestTemplate restTemplate;


    @Value("${fixer.io.api.url.latest}")
    private String fixerURL;

    @Value("${fixer.io.api.access.key}")
    private String fixerApiKey;

    @Autowired
    public FixerIoService(CurrencyRateRepository currencyRateRepository, RestTemplate restTemplate) {
        this.currencyRateRepository = currencyRateRepository;
        this.restTemplate = restTemplate;
    }

    @Scheduled(cron = "${fixer.io.api.schedule.cron}")
    public void updateCurrencyRates() {
        String fullUrl = fixerURL + "?access_key=" + fixerApiKey;
        ResponseEntity<FixerIoResponse> response = restTemplate.getForEntity(fullUrl, FixerIoResponse.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            FixerIoResponse fixerResponse = response.getBody();
            if (fixerResponse != null) {
                fixerResponse.getRates().forEach((currency, rate) -> {
                    CurrencyRate currencyRate = new CurrencyRate();
                    currencyRate.setCurrency(currency);
                    currencyRate.setRate(rate);
                    currencyRate.setTimestamp(LocalDateTime.now());
                    currencyRateRepository.save(currencyRate);
                });
            }
        } else {
            //simulating log for errors
            System.out.println("There was a problem with fixer.io. The status code is " + response.getStatusCode());
        }
    }

}
