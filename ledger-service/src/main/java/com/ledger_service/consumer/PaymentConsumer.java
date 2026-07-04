package com.ledger_service.consumer;

import com.ledger_service.event.PaymentInitiatedEvent;
import com.ledger_service.service.LedgerService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class PaymentConsumer {

    private final LedgerService ledgerService;

    @KafkaListener(
            topics = "payment-initiated",
            groupId = "ledger-group"
    )
    public void consume(PaymentInitiatedEvent event) {

        log.info("Received Payment Event : {}", event);

        ledgerService.processPayment(event);

        log.info("event processed");

    }
}