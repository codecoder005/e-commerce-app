package com.popcorn.ecommerce.notification.producer;

import com.popcorn.ecommerce.kafka.payment.PaymentConfirmation;
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
public class NotificationProducer {
    private final KafkaTemplate<String, PaymentConfirmation> kafkaTemplate;

    @Async
    public void sendNotification(final PaymentConfirmation paymentNotification) {
        log.info("NotificationProducer::sendNotification {}", paymentNotification);
        Message<PaymentConfirmation> message =
                MessageBuilder.withPayload(paymentNotification)
                        .setHeader(KafkaHeaders.TOPIC, "payment-topic")
                        .build();
        kafkaTemplate.send(message);
    }
}
