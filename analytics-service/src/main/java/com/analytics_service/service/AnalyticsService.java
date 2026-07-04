package com.analytics_service.service;

import com.analytics_service.entity.AnalyticsEvent;
import com.analytics_service.repo.AnalyticsRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AnalyticsService {

    private final AnalyticsRepository repository;

    public AnalyticsService(AnalyticsRepository repository) {
        this.repository = repository;
    }

    public void saveCompletedTransaction(String transactionId) {

        AnalyticsEvent event = new AnalyticsEvent(
                transactionId,
                "PAYMENT_COMPLETED",
                LocalDateTime.now()
        );

        repository.save(event);
    }
}