package transactiongateway.event;

import lombok.*;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentFailedEvent {

    private UUID paymentId;
    private String transactionId;
    private String reason;

}