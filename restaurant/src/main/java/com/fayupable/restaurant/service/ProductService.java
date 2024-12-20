package com.fayupable.restaurant.service;

import com.fayupable.restaurant.dto.product.ProductPurchaseRequest;
import com.fayupable.restaurant.dto.product.ProductPurchaseResponse;
import com.fayupable.restaurant.dto.product.ProductRequest;
import com.fayupable.restaurant.dto.product.ProductResponse;
import com.fayupable.restaurant.exception.ProductPurchaseException;
import com.fayupable.restaurant.mapper.product.ProductMapper;
import com.fayupable.restaurant.repository.IProductRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService {
    private final IProductRepository productRepository;
    private final ProductMapper productMapper;

    public Integer createProduct(ProductRequest productRequest) {
        var product = productMapper.toProduct(productRequest);
        return productRepository.save(product).getId();
    }

    public ProductResponse findById(Integer id) {
        return productRepository.findById(id)
                .map(productMapper::toProductResponse)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with id " + id));
    }

    public List<ProductResponse> findAll() {
        return productRepository.findAll().stream()
                .map(productMapper::toProductResponse)
                .collect(Collectors.toList());
    }

    @Transactional(rollbackFor = ProductPurchaseException.class)
    public List<ProductPurchaseResponse> purchaseProducts(
            List<ProductPurchaseRequest> request) {
        var productId = request
                .stream()
                .map(ProductPurchaseRequest::productId)
                .toList();
        var storedProducts = productRepository.findAllByIdInOrderById(productId);
        if (productId.size() != storedProducts.size()) {
            throw new ProductPurchaseException("Some products were not found");
        }
        var sortedRequest = request
                .stream()
                .sorted(Comparator.comparing(ProductPurchaseRequest::productId))
                .toList();
        var purchasedProducts = new ArrayList<ProductPurchaseResponse>();
        for (int i = 0; i < storedProducts.size(); i++) {
            var storedProduct = storedProducts.get(i);
            var purchaseRequest = sortedRequest.get(i);
            if (storedProduct.getAvailableQuantity() < purchaseRequest.quantity()) {
                throw new ProductPurchaseException("Not enough quantity for product with id " + storedProduct.getId());
            }
            var newAvailableQuantity = storedProduct.getAvailableQuantity() - purchaseRequest.quantity();
            storedProduct.setAvailableQuantity(newAvailableQuantity);
            productRepository.save(storedProduct);
            purchasedProducts.add(productMapper.toProductPurchaseResponse(storedProduct, purchaseRequest.quantity()));
            productRepository.save(storedProduct);
        }
        return purchasedProducts;
    }


}
