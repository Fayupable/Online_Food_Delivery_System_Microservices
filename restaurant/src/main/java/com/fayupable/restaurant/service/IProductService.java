package com.fayupable.restaurant.service;

import com.fayupable.restaurant.dto.product.ProductPurchaseRequest;
import com.fayupable.restaurant.dto.product.ProductPurchaseResponse;
import com.fayupable.restaurant.dto.product.ProductRequest;
import com.fayupable.restaurant.dto.product.ProductResponse;

import java.util.List;

public interface IProductService {
    Integer createProduct(ProductRequest productRequest);

    ProductResponse findById(Integer id);

    List<ProductResponse> findAll();

    List<ProductPurchaseResponse> purchaseProducts(List<ProductPurchaseRequest> request);

}
