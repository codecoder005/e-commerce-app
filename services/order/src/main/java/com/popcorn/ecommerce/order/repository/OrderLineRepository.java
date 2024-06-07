package com.popcorn.ecommerce.order.repository;

import com.popcorn.ecommerce.order.orderline.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface OrderLineRepository extends JpaRepository<OrderLine, UUID> {
    @Query(value = """
    SELECT order_line FROM OrderLine order_line
    WHERE order_line.order.orderId = :orderId
    """)
    List<OrderLine> findAllByOrderId(@Param("orderId") UUID orderId);
}
