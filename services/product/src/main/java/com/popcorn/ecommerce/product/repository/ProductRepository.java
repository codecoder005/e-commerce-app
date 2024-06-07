package com.popcorn.ecommerce.product.repository;

import com.popcorn.ecommerce.product.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<ProductEntity, UUID> {
    List<ProductEntity> findAllByIdInOrderById(List<UUID> ids);
}
