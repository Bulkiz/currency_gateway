package com.example.currencygateway.repositories;

import com.example.currencygateway.entities.CurrencyRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CurrencyRateRepository extends JpaRepository<CurrencyRate, Long> {

    CurrencyRate findTopByCurrencyOrderByTimestampDesc(@Param("currency") String currency);

    @Query("SELECT r FROM CurrencyRate r WHERE r.currency = :currency AND r.timestamp >= :startTime")
    List<CurrencyRate> findRatesByCurrencyForPeriod(@Param("currency") String currency, @Param("startTime") LocalDateTime startTime);
}
