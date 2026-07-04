package transactiongateway.event;

import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentInitiatedEvent {

    private UUID paymentId;

    private String transactionId;

    private Long senderId;

    private Long receiverId;

    private BigDecimal amount;

    private String currency;

}