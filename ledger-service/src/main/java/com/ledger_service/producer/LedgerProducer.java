package com.ledger_service.producer;

import com.ledger_service.event.PaymentCompletedEvent;
import com.ledger_service.event.PaymentFailedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LedgerProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void publishPaymentCompleted(PaymentCompletedEvent event) {

        kafkaTemplate.send(
                "payment-completed",
                event.getTransactionId(),
                event
        );

    }

    public void publishPaymentFailed(PaymentFailedEvent event) {

        kafkaTemplate.send(
                "payment-failed",
                event.getTransactionId(),
                event
        );

    }

}