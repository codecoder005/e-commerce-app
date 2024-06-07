package com.popcorn.ecommerce.kafka.consumer;

import com.popcorn.ecommerce.email.service.EmailService;
import com.popcorn.ecommerce.kafka.order.OrderConfirmation;
import com.popcorn.ecommerce.kafka.payment.PaymentConfirmation;
import com.popcorn.ecommerce.notification.Notification;
import com.popcorn.ecommerce.notification.NotificationRepository;
import com.popcorn.ecommerce.notification.NotificationType;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationConsumer {
    private final NotificationRepository notificationRepository;
    private final EmailService emailService;

    @KafkaListener(topics = "payment-topic")
    public void consumePaymentSuccessNotification(PaymentConfirmation paymentConfirmation) throws MessagingException {
        log.info("NotificationConsumer::consumePaymentSuccessNotification {}", paymentConfirmation);
        notificationRepository.save(
                Notification.builder()
                        .id(UUID.randomUUID())
                        .type(NotificationType.PAYMENT_CONFIRMATION)
                        .notificationTime(LocalDateTime.now())
                        .paymentConfirmation(paymentConfirmation)
                        .build()
        );
        // todo send email
        emailService.sendPaymentSuccessEmail(
                paymentConfirmation.getCustomer().getEmail(),
                paymentConfirmation.getCustomer().getName(),
                paymentConfirmation.getAmount(),
                paymentConfirmation.getOrderId().toString()
        );
    }

    @KafkaListener(topics = "order-topic")
    public void consumeOrderConfirmationNotification(OrderConfirmation orderConfirmation) throws MessagingException {
        log.info("NotificationConsumer::consumeOrderConfirmationNotification {}", orderConfirmation);
        notificationRepository.save(
                Notification.builder()
                        .id(UUID.randomUUID())
                        .type(NotificationType.ORDER_CONFIRMATION)
                        .notificationTime(LocalDateTime.now())
                        .orderConfirmation(orderConfirmation)
                        .build()
        );

        // todo send email
        emailService.sendOrderConfirmationEmail(
                orderConfirmation.getCustomerEmail(),
                orderConfirmation.getCustomerName(),
                orderConfirmation.getAmount(),
                orderConfirmation.getOrderId().toString(),
                orderConfirmation.getProducts()
        );
    }
}
