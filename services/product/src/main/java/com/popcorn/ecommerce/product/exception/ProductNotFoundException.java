package com.popcorn.ecommerce.product.exception;

public class ProductNotFoundException extends AppException{
    public ProductNotFoundException(String message) {
        super(message);
    }
}
