package com.example.currencygateway.repositories;

import com.example.currencygateway.entities.RequestLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestLogRepository extends JpaRepository<RequestLog, String> {

}
