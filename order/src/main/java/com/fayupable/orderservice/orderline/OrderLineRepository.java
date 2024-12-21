package com.fayupable.orderservice.orderline;

import com.fayupable.orderservice.entity.orderline.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderLineRepository extends JpaRepository<OrderLine, Long> {
}
