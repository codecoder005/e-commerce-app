package com.popcorn.ecommerce.payment.repository;

import com.popcorn.ecommerce.payment.payment.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PaymentRepository extends JpaRepository<PaymentEntity, UUID> {

}
