package com.popcorn.ecommerce.order.controller;

import com.popcorn.ecommerce.order.dto.OrderLineDto;
import com.popcorn.ecommerce.order.exception.AppException;
import com.popcorn.ecommerce.order.repository.OrderLineRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/order-lines")
@RequiredArgsConstructor
@Slf4j
public class OrderLineController {
    private final OrderLineRepository orderLineRepository;

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<OrderLineDto>> getAllOrderLines() {
        log.info("OrderLineController::getAllOrderLines");
        return ResponseEntity.status(HttpStatus.OK)
                .body(orderLineRepository.findAll().stream().map(
                        entity -> OrderLineDto.builder()
                                .orderLineId(entity.getOrderLineId())
                                //.orderId(entity.getOrder().getOrderId())
                                .productId(entity.getProductId())
                                .quantity(entity.getQuantity())
                                .build()
                ).toList());
    }

    @GetMapping(value = "/{orderLineId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<OrderLineDto> getOrderLineById(@PathVariable("orderLineId") UUID orderLineId) {
        log.info("OrderLineController::getOrderLineById");
        var entity = orderLineRepository.findById(orderLineId)
                .orElseThrow(() -> new AppException("No order-line found with orderLineId: " + orderLineId));
        return ResponseEntity.status(HttpStatus.OK)
                .body(OrderLineDto.builder()
                        .orderLineId(entity.getOrderLineId())
                        .productId(entity.getProductId())
                        .quantity(entity.getQuantity())
                        .build());
    }
}
