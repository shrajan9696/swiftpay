package com.analytics_service.consumer;

import com.analytics_service.event.PaymentCompletedEvent;
import com.analytics_service.service.AnalyticsService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class PaymentCompletedConsumer {

    private final AnalyticsService analyticsService;

    public PaymentCompletedConsumer(AnalyticsService analyticsService) {
        this.analyticsService = analyticsService;
    }

    @KafkaListener(
            topics = "payment-completed",
            groupId = "analytics-group"
    )
    public void consume(PaymentCompletedEvent event) {

        System.out.println("Analytics Event Received : " + event.getTransactionId());

        analyticsService.saveCompletedTransaction(event.getTransactionId());
    }
}