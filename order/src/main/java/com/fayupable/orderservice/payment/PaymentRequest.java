package com.fayupable.orderservice.payment;

import com.fayupable.orderservice.entity.order.PaymentMethod;
import com.fayupable.orderservice.user.UserResponse;

import java.math.BigDecimal;

public record PaymentRequest(
        BigDecimal amount,
        PaymentMethod paymentMethod,
        Integer orderId,
        String orderReference,
        UserResponse user
) {
}
