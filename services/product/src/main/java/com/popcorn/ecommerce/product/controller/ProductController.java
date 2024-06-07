package com.popcorn.ecommerce.product.controller;

import com.popcorn.ecommerce.product.dto.ProductDto;
import com.popcorn.ecommerce.product.model.CreateProductRequest;
import com.popcorn.ecommerce.product.model.CreateProductResponse;
import com.popcorn.ecommerce.product.model.PurchaseProductRequest;
import com.popcorn.ecommerce.product.model.PurchaseProductResponse;
import com.popcorn.ecommerce.product.service.ProductService;
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
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
@Slf4j
public class ProductController {
    private final ProductService productService;

    @PostMapping(
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<CreateProductResponse> createProduct(
            @Valid @RequestBody CreateProductRequest request
    ) {
        log.info("ProductController::createProduct");
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(productService.createProduct(request));
    }

    @PostMapping(
            value = "/purchase",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<List<PurchaseProductResponse>> purchaseProducts(
            @Valid @RequestBody List<PurchaseProductRequest> request
    ) {
        log.info("ProductController::purchaseProducts");
        return ResponseEntity.status(HttpStatus.OK)
                .body(productService.purchaseProducts(request));
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        log.info("ProductController::getAllProducts");
        return ResponseEntity.status(HttpStatus.OK)
                .body(productService.getAllProducts());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable("id") UUID id) {
        log.info("ProductController::getProductById");
        return ResponseEntity.status(HttpStatus.OK)
                .body(productService.getProductById(id));
    }
}
