package com.fayupable.orderservice.mapper;

import com.fayupable.orderservice.dto.OrderRequest;
import com.fayupable.orderservice.dto.OrderResponse;
import com.fayupable.orderservice.entity.order.Order;
import org.springframework.stereotype.Service;

@Service
public class OrderMapper {
    public Order toOrder(OrderRequest orderRequest) {
        return Order.builder()
                .id(orderRequest.id())
                .userId(orderRequest.userId())
                .reference(orderRequest.reference())
                .totalAmount(orderRequest.totalAmount())
                .paymentMethod(orderRequest.paymentMethod())
                .build();

    }

    public OrderResponse fromOrder(Order order) {
        return new OrderResponse(
                order.getId(),
                order.getReference(),
                order.getTotalAmount(),
                order.getPaymentMethod(),
                order.getUserId()
        );

    }
}
