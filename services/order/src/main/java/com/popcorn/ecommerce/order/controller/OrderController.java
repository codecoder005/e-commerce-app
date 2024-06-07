package com.popcorn.ecommerce.order.controller;

import com.popcorn.ecommerce.order.dto.OrderDto;
import com.popcorn.ecommerce.order.model.PlaceOrderRequest;
import com.popcorn.ecommerce.order.model.PlaceOrderResponse;
import com.popcorn.ecommerce.order.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
@Slf4j
public class OrderController {
    private final OrderService orderService;

    @PostMapping(
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<PlaceOrderResponse> placeOrder(
            @Valid @RequestBody PlaceOrderRequest request
    ) {
        log.info("OrderController::placeOrder");
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(orderService.placeOrder(request));
    }


    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<OrderDto>> getAllOrders() {
        log.info("OrderController::getAllOrders");
        return ResponseEntity.status(HttpStatus.OK)
                .body(orderService.getAllOrders());
    }

    @GetMapping(value = "/{orderId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<OrderDto> getOrderById(@PathVariable("orderId") UUID orderId) {
        log.info("OrderController::getOrderById");
        return ResponseEntity.status(HttpStatus.OK)
                .body(orderService.getOrderById(orderId));
    }


}
