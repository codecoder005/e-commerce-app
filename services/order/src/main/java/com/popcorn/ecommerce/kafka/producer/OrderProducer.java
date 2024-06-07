package com.popcorn.ecommerce.kafka.producer;

import com.popcorn.ecommerce.kafka.order.OrderConfirmation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderProducer {
    private final KafkaTemplate<String, OrderConfirmation> kafkaTemplate;

    @Async
    public void sendOrderConfirmation(OrderConfirmation orderConfirmation) {
        log.info("OrderProducer::sendOrderConfirmation {}", orderConfirmation);
        Message<OrderConfirmation> message = MessageBuilder.withPayload(orderConfirmation)
                .setHeader(KafkaHeaders.TOPIC, "order-topic").build();

        kafkaTemplate.send(message);
    }
}
