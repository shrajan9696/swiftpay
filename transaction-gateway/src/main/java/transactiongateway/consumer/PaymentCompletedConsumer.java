package transactiongateway.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import transactiongateway.event.PaymentCompletedEvent;
import transactiongateway.service.PaymentStatusService;

@Component
@RequiredArgsConstructor
@Slf4j
public class PaymentCompletedConsumer {

    private final PaymentStatusService paymentStatusService;

    @KafkaListener(
            topics = "payment-completed",
            groupId = "transaction-gateway-success",
            properties = {
                    "spring.json.value.default.type=transactiongateway.event.PaymentCompletedEvent"
            }
    )
    public void consume(PaymentCompletedEvent event) {

        log.info("Payment Completed Event Received : {}", event);

        paymentStatusService.markSuccess(event.getTransactionId());

    }

}