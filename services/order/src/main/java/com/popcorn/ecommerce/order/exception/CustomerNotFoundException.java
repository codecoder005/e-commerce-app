package com.popcorn.ecommerce.order.exception;

public class CustomerNotFoundException extends AppException {
    public CustomerNotFoundException(String message) {
        super(message);
    }
}
