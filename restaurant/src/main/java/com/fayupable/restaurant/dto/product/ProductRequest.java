package com.fayupable.restaurant.dto.product;

public record ProductRequest(
        Integer id,
        String name,
        String description,
        Double price,
        double availableQuantity,
        Integer categoryId,
        String restaurantId
) {
}
