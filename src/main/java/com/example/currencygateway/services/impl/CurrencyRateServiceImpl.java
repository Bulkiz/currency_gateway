package com.example.currencygateway.services.impl;

import com.example.currencygateway.dtos.CurrencyRateResponse;
import com.example.currencygateway.entities.CurrencyRate;
import com.example.currencygateway.mappers.CurrencyRateMapper;
import com.example.currencygateway.repositories.CurrencyRateRepository;
import com.example.currencygateway.services.CurrencyRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CurrencyRateServiceImpl implements CurrencyRateService {

    private final CurrencyRateRepository currencyRateRepository;
    private final CurrencyRateMapper currencyRateMapper;


    @Autowired
    public CurrencyRateServiceImpl(CurrencyRateRepository currencyRateRepository, CurrencyRateMapper currencyRateMapper) {
        this.currencyRateRepository = currencyRateRepository;
        this.currencyRateMapper = currencyRateMapper;

    }

    @Override
    public CurrencyRateResponse getCurrentRateByCurrency(String currency) {
        return currencyRateMapper.mapToResponse(currencyRateRepository.findTopByCurrencyOrderByTimestampDesc(currency));
    }

    @Override
    public List<CurrencyRateResponse> findRatesByCurrencyAndPeriod(String currency, Integer period) {
        LocalDateTime startTime = LocalDateTime.now().minusHours(period);
        List<CurrencyRate> ratesByCurrencyAndPeriod = currencyRateRepository.findRatesByCurrencyForPeriod(currency, startTime);
        return ratesByCurrencyAndPeriod.stream().map(currencyRateMapper::mapToResponse).toList();
    }
}
