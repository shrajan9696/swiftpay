package transactiongateway.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import transactiongateway.event.PaymentFailedEvent;
import transactiongateway.service.PaymentStatusService;

@Component
@RequiredArgsConstructor
@Slf4j
public class PaymentFailedConsumer {

    private final PaymentStatusService paymentStatusService;

    @KafkaListener(
            topics = "payment-failed",
            groupId = "transaction-gateway-failed",
            properties = {
                    "spring.json.value.default.type=transactiongateway.event.PaymentFailedEvent"
            }
    )
    public void consume(PaymentFailedEvent event) {

        log.info("Payment Failed Event Received : {}", event);

        paymentStatusService.markFailed(event.getTransactionId());

    }

}