package com.popcorn.ecommerce.payment.service;

import com.popcorn.ecommerce.kafka.payment.PaymentConfirmation;
import com.popcorn.ecommerce.notification.producer.NotificationProducer;
import com.popcorn.ecommerce.payment.model.PaymentRequest;
import com.popcorn.ecommerce.payment.model.PaymentResponse;
import com.popcorn.ecommerce.payment.payment.PaymentEntity;
import com.popcorn.ecommerce.payment.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final NotificationProducer notificationProducer;

    public PaymentResponse makePayment(PaymentRequest request) {
        log.info("PaymentService::makePayment");
        var payment = paymentRepository.save(
                PaymentEntity.builder()
                        .orderId(request.getOrderId())
                        .customerId(request.getCustomer().getId())
                        .amount(request.getAmount())
                        .paymentMethod(request.getPaymentMethod())
                        .build()
        );
        var notification = PaymentConfirmation.builder()
                .orderId(request.getOrderId())
                .customerId(request.getCustomer().getId())
                .amount(request.getAmount())
                .customer(request.getCustomer())
                .paymentMethod(request.getPaymentMethod())
                .build();
        notificationProducer.sendNotification(notification);
        return PaymentResponse.builder()
                .paymentId(payment.getPaymentId())
                .amount(payment.getAmount())
                .status("SUCCESS")
                .build();
    }
}
