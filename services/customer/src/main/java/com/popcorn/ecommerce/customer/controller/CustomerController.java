package com.popcorn.ecommerce.customer.controller;

import com.popcorn.ecommerce.customer.dto.CustomerDto;
import com.popcorn.ecommerce.customer.model.CreateCustomerRequest;
import com.popcorn.ecommerce.customer.model.CreateCustomerResponse;
import com.popcorn.ecommerce.customer.model.UpdateCustomerRequest;
import com.popcorn.ecommerce.customer.model.UpdateCustomerResponse;
import com.popcorn.ecommerce.customer.service.CustomerService;
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
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
@Slf4j
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CreateCustomerResponse> createCustomer(
            @Valid @RequestBody CreateCustomerRequest request
    ) {
        log.info("CustomerController::createCustomer");
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(customerService.createCustomer(request));
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<CustomerDto>> getAllCustomers() {
        log.info("CustomerController::getAllCustomers");
        return ResponseEntity.status(HttpStatus.OK)
                .body(customerService.getAllCustomers());
    }

    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<CustomerDto> getCustomerById(@PathVariable UUID id) {
        log.info("CustomerController::getCustomerById");
        return ResponseEntity.status(HttpStatus.OK)
                .body(customerService.getCustomerById(id));
    }

    @PutMapping(value = "/{id}",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<UpdateCustomerResponse> updateCustomer(
            @PathVariable UUID id,
            @RequestBody UpdateCustomerRequest request
    ) {
        log.info("CustomerController::updateCustomer");
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(customerService.updateCustomer(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable UUID id) {
        log.info("CustomerController::deleteCustomer");
        customerService.deleteCustomer(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
