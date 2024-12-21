package com.fayupable.orderservice.product;

import com.fayupable.orderservice.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.springframework.http.HttpHeaders.*;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Service
@RequiredArgsConstructor
public class ProductClient {

    @Value("${application.config.restaurant-url}")
    private String restaurantUrl;
    private final RestTemplate restTemplate;

    public List<PurchaseResponse> purchaseProduct(List<PurchaseRequest> purchaseRequests) {

        HttpHeaders headers = new HttpHeaders();
        headers.set(CONTENT_TYPE, APPLICATION_JSON_VALUE);

        HttpEntity<List<PurchaseRequest>> request = new HttpEntity<>(purchaseRequests, headers);
        ParameterizedTypeReference<List<PurchaseResponse>> responseType =
                new ParameterizedTypeReference<>() {
                };

        ResponseEntity<List<PurchaseResponse>> response = restTemplate.exchange(
                restaurantUrl + "/api/v1/product/purchase",
                POST,
                request,
                responseType
        );

        if (response.getStatusCode().isError()) {
            throw new BusinessException("An error occurred while purchasing the product" + response.getStatusCode());
        }
        return response.getBody();

    }
}
