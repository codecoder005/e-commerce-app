package com.popcorn.ecommerce.order.service;

import com.popcorn.ecommerce.order.client.CustomerClient;
import com.popcorn.ecommerce.order.client.ProductClient;
import com.popcorn.ecommerce.order.dto.OrderDto;
import com.popcorn.ecommerce.order.entity.OrderEntity;
import com.popcorn.ecommerce.order.exception.CustomerNotFoundException;
import com.popcorn.ecommerce.order.exception.OrderNotFoundException;
import com.popcorn.ecommerce.kafka.order.OrderConfirmation;
import com.popcorn.ecommerce.kafka.producer.OrderProducer;
import com.popcorn.ecommerce.order.model.PlaceOrderRequest;
import com.popcorn.ecommerce.order.model.PlaceOrderResponse;
import com.popcorn.ecommerce.order.model.PurchaseProductResponse;
import com.popcorn.ecommerce.order.orderline.OrderLine;
import com.popcorn.ecommerce.order.repository.OrderLineRepository;
import com.popcorn.ecommerce.order.repository.OrderRepository;
import com.popcorn.ecommerce.payment.client.PaymentClient;
import com.popcorn.ecommerce.payment.model.PaymentRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderLineRepository orderLineRepository;
    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderProducer orderProducer;
    private final PaymentClient paymentClient;

    public PlaceOrderResponse placeOrder(PlaceOrderRequest request) {
        log.info("OrderService::placeOrder");
        // check if customer exists
        var customer = customerClient.findCustomerById(request.getCustomerId())
                .orElseThrow(() -> new CustomerNotFoundException("Cannot Place order. Customer not found with id: " + request.getCustomerId()));

        // check if the products exists -> product-service
        var purchaseProductResponses = this.productClient.purchaseProducts(request.getProducts());

        // save order
        var orderAmount = purchaseProductResponses.stream()
                .map(p -> p.getPrice().multiply(new BigDecimal(p.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        var savedOrder = this.orderRepository.save(
                OrderEntity.builder()
                        .customerId(request.getCustomerId())
                        .amount(orderAmount)
                        .paymentMethod(request.getPaymentMethod())
                        .build()
        );
        // save order lines
        List<OrderLine> orderLines = new ArrayList<>();
        for(PurchaseProductResponse response: purchaseProductResponses) {
            OrderLine orderLine = OrderLine.builder()
                    .order(OrderEntity.builder().orderId(savedOrder.getOrderId()).build())
                    .productId(response.getId())
                    .quantity(response.getQuantity())
                    .build();
            orderLines.add(orderLine);
        }

        orderLineRepository.saveAll(orderLines);

        // todo initiate payment
        paymentClient.makePayment(
                PaymentRequest.builder()
                        .orderId(savedOrder.getOrderId())
                        .amount(orderAmount)
                        .paymentMethod(request.getPaymentMethod())
                        .customer(customer)
                        .build()
        );

        // todo send notification --> notification-service (kafka)
        orderProducer.sendOrderConfirmation(
                OrderConfirmation.builder()
                        .orderId(savedOrder.getOrderId())
                        .customerId(request.getCustomerId())
                        .amount(orderAmount)
                        .paymentMethod(request.getPaymentMethod())
                        .customerEmail(customer.getEmail())
                        .customerName(customer.getName())
                        .products(purchaseProductResponses)
                .build()
        );

        return PlaceOrderResponse.builder()
                .orderId(savedOrder.getOrderId())
                .amount(orderAmount)
                .build();
    }

    public OrderDto getOrderById(UUID orderId) {
        log.info("OrderService::getOrderById");
        OrderEntity order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("No order found with orderId: " + orderId));
        var orderLines = orderLineRepository.findAllByOrderId(orderId);
        return OrderDto.builder()
                .orderId(orderId)
                .customerId(order.getCustomerId())
                .amount(order.getAmount())
                .paymentMethod(order.getPaymentMethod())
                .orderDate(order.getOrderDate())
                .lastModifiedDate(order.getLastModifiedDate())
                .orderLine(orderLines)
                .build();
    }

    public List<OrderDto> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(
                        entity -> OrderDto.builder()
                                .orderId(entity.getOrderId())
                                .customerId(entity.getCustomerId())
                                .amount(entity.getAmount())
                                .paymentMethod(entity.getPaymentMethod())
                                .orderDate(entity.getOrderDate())
                                .lastModifiedDate(entity.getLastModifiedDate())
                                .orderLine(entity.getOrderLine())
                                .build()
                )
                .toList();
    }
}
