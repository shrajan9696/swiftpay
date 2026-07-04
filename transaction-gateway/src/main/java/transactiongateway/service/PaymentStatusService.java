package transactiongateway.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import transactiongateway.constants.PaymentStatus;
import transactiongateway.entity.Payments;
import transactiongateway.repository.PaymentRepository;

@Service
@RequiredArgsConstructor
public class PaymentStatusService {

    private final PaymentRepository paymentRepository;

    @Transactional
    public void markSuccess(String transactionId) {

        Payments payment = paymentRepository
                .findByTransactionId(transactionId)
                .orElseThrow(() -> new RuntimeException("Payment not found"));

        payment.setStatus(PaymentStatus.SUCCESS);

        paymentRepository.save(payment);

    }

    @Transactional
    public void markFailed(String transactionId) {

        Payments payment = paymentRepository
                .findByTransactionId(transactionId)
                .orElseThrow(() -> new RuntimeException("Payment not found"));

        payment.setStatus(PaymentStatus.FAILED);

        paymentRepository.save(payment);

    }

}