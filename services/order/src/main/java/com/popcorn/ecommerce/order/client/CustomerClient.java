package com.popcorn.ecommerce.order.client;

import com.popcorn.ecommerce.order.dto.CustomerDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;
import java.util.UUID;

@FeignClient(
        name = "customer-service",
        url = "${application.config.customer-url}"
)
public interface CustomerClient {
    @GetMapping("/{id}")
    Optional<CustomerDto> findCustomerById(@PathVariable("id") UUID id);
}
