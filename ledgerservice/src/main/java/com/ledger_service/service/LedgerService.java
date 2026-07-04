package com.ledger_service.service;

import com.ledger_service.entity.Account;
import com.ledger_service.event.PaymentCompletedEvent;
import com.ledger_service.event.PaymentFailedEvent;
import com.ledger_service.event.PaymentInitiatedEvent;
import com.ledger_service.producer.LedgerProducer;
import com.ledger_service.repository.AccountRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LedgerService {

    private final AccountRepository accountRepository;
    private final LedgerProducer ledgerProducer;

    @Transactional
    public void processPayment(PaymentInitiatedEvent event) {

        try {

            Account sender = accountRepository.findById(event.getSenderId())
                    .orElseThrow(() -> new RuntimeException("Sender account not found"));

            Account receiver = accountRepository.findById(event.getReceiverId())
                    .orElseThrow(() -> new RuntimeException("Receiver account not found"));

            if (sender.getBalance().compareTo(event.getAmount()) < 0) {
                throw new RuntimeException("Insufficient Balance");
            }

            sender.setBalance(sender.getBalance().subtract(event.getAmount()));
            receiver.setBalance(receiver.getBalance().add(event.getAmount()));

            accountRepository.save(sender);
            accountRepository.save(receiver);

            ledgerProducer.publishPaymentCompleted(

                    PaymentCompletedEvent.builder()
                            .paymentId(event.getPaymentId())
                            .transactionId(event.getTransactionId())
                            .build()

            );

            System.out.println("Payment Processed Successfully");

        } catch (Exception ex) {

            ledgerProducer.publishPaymentFailed(

                    PaymentFailedEvent.builder()
                            .paymentId(event.getPaymentId())
                            .transactionId(event.getTransactionId())
                            .reason(ex.getMessage())
                            .build()

            );

            System.out.println("Payment Failed : " + ex.getMessage());
        }

    }
}