package transactiongateway.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import transactiongateway.dto.PaymentRequest;
import transactiongateway.dto.PaymentResponse;
import transactiongateway.entity.Payments;
import transactiongateway.event.PaymentInitiatedEvent;
import transactiongateway.exception.DuplicateTransactionException;
import transactiongateway.mapper.PaymentMapper;
import transactiongateway.producer.PaymentProducer;
import transactiongateway.repository.PaymentRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;
    private final IdempotencyService idempotencyService;
    private final PaymentProducer paymentProducer;

    public PaymentResponse createPayment(PaymentRequest request) {


        if (!idempotencyService.acquireTransaction(request.getTransactionId())) {
            throw new DuplicateTransactionException(
                    "Duplicate transaction: " + request.getTransactionId());
        }


        Payments payment = paymentMapper.toEntity(request);
        payment = paymentRepository.save(payment);

        PaymentInitiatedEvent event =
                PaymentInitiatedEvent.builder()
                        .paymentId(payment.getId())
                        .transactionId(payment.getTransactionId())
                        .senderId(payment.getSenderId())
                        .receiverId(payment.getReceiverId())
                        .amount(payment.getAmount())
                        .currency(payment.getCurrency())
                        .build();

        paymentProducer.publish(event);
        return paymentMapper.toResponse(payment);
    }

    public List<Payments> getTransactionHistory(Long userId) {

        return paymentRepository
                .findBySenderIdOrReceiverIdOrderByCreatedAtDesc(userId, userId);
    }
}