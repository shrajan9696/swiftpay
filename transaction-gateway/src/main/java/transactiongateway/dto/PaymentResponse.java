package transactiongateway.dto;

import transactiongateway.constants.PaymentStatus;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentResponse {

    private String transactionId;

    private PaymentStatus status;

    private String message;

    private UUID paymentId;
}