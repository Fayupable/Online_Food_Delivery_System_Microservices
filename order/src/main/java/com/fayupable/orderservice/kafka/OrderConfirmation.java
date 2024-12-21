package com.fayupable.orderservice.kafka;

import com.fayupable.orderservice.entity.order.PaymentMethod;
import com.fayupable.orderservice.product.PurchaseResponse;
import com.fayupable.orderservice.user.UserResponse;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        UserResponse user,
        List<PurchaseResponse> products
) {
}
