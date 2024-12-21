package com.fayupable.orderservice.service;

import com.fayupable.orderservice.dto.OrderResponse;
import com.fayupable.orderservice.exception.BusinessException;
import com.fayupable.orderservice.kafka.OrderConfirmation;
import com.fayupable.orderservice.kafka.OrderProducer;
import com.fayupable.orderservice.mapper.OrderMapper;
import com.fayupable.orderservice.orderline.OrderLineRequest;
import com.fayupable.orderservice.orderline.OrderLineService;
import com.fayupable.orderservice.product.ProductClient;
import com.fayupable.orderservice.product.PurchaseRequest;
import com.fayupable.orderservice.user.IUserClient;
import com.fayupable.orderservice.dto.OrderRequest;
import com.fayupable.orderservice.repository.IOrderRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService implements IOrderService {
    private final IOrderRepository orderRepository;
    private final IUserClient userClient;
    private final ProductClient productClient;
    private final OrderMapper orderMapper;
    private final OrderLineService orderLineService;
    private final OrderProducer orderProducer;


    @Override
    public Integer createOrder(OrderRequest orderRequest) {
        //check the customer --> openfeign

        var user = this.userClient.findUserById(orderRequest.userId())
                .orElseThrow(() -> new BusinessException("Cannot create order:: No user found with id: " + orderRequest.userId()));

        //purchase the product --> product microservice (RestTemplate)
        var purchasedProducts = this.productClient.purchaseProduct(orderRequest.products());
        var order = this.orderRepository.save(orderMapper.toOrder(orderRequest));

        //persist order

        for (PurchaseRequest purchaseRequest : orderRequest.products()) {
            orderLineService.saveOrderLine(
                    new OrderLineRequest(
                            null,
                            order.getId(),
                            purchaseRequest.productId(),
                            purchaseRequest.quantity()
                    )
            );

        }

        //persist order lines

        //start payment process

        //send the order confirmation --> notification microservice(kafka)

        orderProducer.sendOrderConfirmation(
                new OrderConfirmation(
                        orderRequest.reference(),
                        orderRequest.totalAmount(),
                        orderRequest.paymentMethod(),
                        user,
                        purchasedProducts
                )
        );
        return order.getId();
    }


    @Override
    public List<OrderResponse> findAll() {
        return orderRepository.findAll()
                .stream()
                .map(orderMapper::fromOrder)
                .collect(Collectors.toList());

    }

    @Override
    public OrderResponse findById(Integer orderId) {
        return orderRepository.findById(orderId)
                .map(orderMapper::fromOrder)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Order with id %d not found", orderId)));
    }
}
