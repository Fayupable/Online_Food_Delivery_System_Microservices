package com.fayupable.restaurant.dto.product;

public record ProductPurchaseResponse(
        Integer productId,
        String productName,
        String productDescription,
        Double productPrice,
        double quantity
) {
}
