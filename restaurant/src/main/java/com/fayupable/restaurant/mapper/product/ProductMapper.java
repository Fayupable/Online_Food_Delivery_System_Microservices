package com.fayupable.restaurant.mapper.product;

import com.fayupable.restaurant.dto.product.ProductPurchaseResponse;
import com.fayupable.restaurant.dto.product.ProductRequest;
import com.fayupable.restaurant.dto.product.ProductResponse;
import com.fayupable.restaurant.entity.Category;
import com.fayupable.restaurant.entity.Product;
import com.fayupable.restaurant.entity.Restaurant;
import org.springframework.stereotype.Service;

@Service
public class ProductMapper {
    public Product toProduct(ProductRequest productRequest) {
        return Product.builder()
                .id(productRequest.id())
                .name(productRequest.name())
                .description(productRequest.description())
                .price(productRequest.price())
                .availableQuantity(productRequest.availableQuantity())
                .category(
                        Category.builder()
                                .id(productRequest.categoryId())
                                .build()
                )
                .restaurant(
                        Restaurant.builder()
                                .id(String.valueOf(productRequest.restaurantId()))
                                .build()
                )
                .build();
    }

    public ProductResponse toProductResponse(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getAvailableQuantity(),
                product.getCategory().getId(),
                product.getCategory().getName(),
                product.getCategory().getDescription(),
                product.getRestaurant().getId()
        );
    }

    public ProductPurchaseResponse toProductPurchaseResponse(Product product, double quantity) {
        return new ProductPurchaseResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                quantity
        );

    }

}


