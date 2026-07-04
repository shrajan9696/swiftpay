package transactiongateway.producer;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import transactiongateway.constants.KafkaTopics;
import transactiongateway.event.PaymentInitiatedEvent;

@Service
@RequiredArgsConstructor
public class PaymentProducer {

    private final KafkaTemplate<String, PaymentInitiatedEvent> kafkaTemplate;

    public void publish(PaymentInitiatedEvent event) {

        kafkaTemplate.send(
                KafkaTopics.PAYMENT_INITIATED,
                event.getTransactionId(),
                event
        );

    }

}