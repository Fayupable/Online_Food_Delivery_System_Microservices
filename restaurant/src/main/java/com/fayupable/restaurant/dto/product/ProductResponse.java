package com.fayupable.restaurant.dto.product;

public record ProductResponse(
        Integer id,
        String name,
        String description,
        Double price,
        double availableQuantity,
        Integer categoryId,
        String categoryName,
        String categoryDescription,
        String restaurantId
) {
}
