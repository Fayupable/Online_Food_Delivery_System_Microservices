package com.fayupable.orderservice.service;

import com.fayupable.orderservice.dto.OrderRequest;
import com.fayupable.orderservice.dto.OrderResponse;
import jakarta.validation.Valid;

import java.util.List;

public interface IOrderService {
    Integer createOrder( OrderRequest orderRequest);

    List<OrderResponse> findAll();

    OrderResponse findById(Integer orderId);
}
