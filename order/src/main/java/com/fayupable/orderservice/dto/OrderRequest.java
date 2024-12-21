package com.fayupable.orderservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fayupable.orderservice.entity.order.PaymentMethod;
import com.fayupable.orderservice.product.PurchaseRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.*;

@JsonInclude(Include.NON_EMPTY)
public record OrderRequest(
        Integer id,
        String reference,
        @Positive(message = "Total amount must be positive")
        BigDecimal totalAmount,
        @NotNull(message = "Payment method must be provided")
        PaymentMethod paymentMethod,
        @NotNull(message = "User ID must be provided")
        @NotEmpty(message = "User ID must not be empty")
        @NotBlank(message = "User ID must not be blank")
        String userId,
        @NotEmpty(message = "You should at least purchase one product")
        List<PurchaseRequest> products
) {
}
