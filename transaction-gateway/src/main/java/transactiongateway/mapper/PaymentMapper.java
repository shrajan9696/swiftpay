package transactiongateway.mapper;

import org.springframework.stereotype.Component;
import transactiongateway.constants.PaymentStatus;
import transactiongateway.dto.PaymentRequest;
import transactiongateway.dto.PaymentResponse;
import transactiongateway.entity.Payments;

@Component
public class PaymentMapper {

    public Payments toEntity(PaymentRequest request) {

        return Payments.builder()
                .transactionId(request.getTransactionId())
                .senderId(request.getSenderId())
                .receiverId(request.getReceiverId())
                .amount(request.getAmount())
                .currency(request.getCurrency())
                .status(PaymentStatus.PENDING)
                .build();
    }

    public PaymentResponse toResponse(Payments payment) {

        return PaymentResponse.builder()
                .paymentId(payment.getId())
                .transactionId(payment.getTransactionId())
                .status(payment.getStatus())
                .message("Payment accepted for processing.")
                .build();
    }
}