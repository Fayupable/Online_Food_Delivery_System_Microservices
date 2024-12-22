package com.fayupable.notification.kafka.payment;

import java.math.BigDecimal;

public record PaymentConfirmation(
        String orderReference,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        String userFirstName,
        String userLastName,
        String userEmail
) {
}
