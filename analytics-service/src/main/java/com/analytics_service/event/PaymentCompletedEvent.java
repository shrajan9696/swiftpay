package com.analytics_service.event;

import java.util.UUID;

public class PaymentCompletedEvent {

    private UUID paymentId;
    private String transactionId;

    public PaymentCompletedEvent() {
    }

    public PaymentCompletedEvent(UUID paymentId, String transactionId) {
        this.paymentId = paymentId;
        this.transactionId = transactionId;
    }

    public UUID getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(UUID paymentId) {
        this.paymentId = paymentId;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }
}