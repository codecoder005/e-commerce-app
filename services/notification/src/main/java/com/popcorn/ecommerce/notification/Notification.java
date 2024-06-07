package com.popcorn.ecommerce.notification;

import com.popcorn.ecommerce.kafka.order.OrderConfirmation;
import com.popcorn.ecommerce.kafka.payment.PaymentConfirmation;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(value = "notifications")
@ToString
public class Notification {
    @Id
    private UUID id;

    private NotificationType type;
    private LocalDateTime notificationTime;
    private OrderConfirmation orderConfirmation;
    private PaymentConfirmation paymentConfirmation;
}
