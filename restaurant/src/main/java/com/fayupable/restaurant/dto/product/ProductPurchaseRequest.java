package com.fayupable.restaurant.dto.product;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ProductPurchaseRequest(
        @NotNull(message = "productId is required")
        Integer productId,
        @Positive(message = "quantity must be greater than zero")
        double quantity
) {
}
