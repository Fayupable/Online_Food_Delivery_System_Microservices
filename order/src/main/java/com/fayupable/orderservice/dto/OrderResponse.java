package com.fayupable.orderservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fayupable.orderservice.entity.order.PaymentMethod;

import java.math.BigDecimal;

import static com.fasterxml.jackson.annotation.JsonInclude.*;

@JsonInclude(Include.NON_NULL)
public record OrderResponse(
        Integer id,
        String reference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        String userId
) {
}
