package com.popcorn.ecommerce.order.exception;

public class OrderNotFoundException extends AppException{
    public OrderNotFoundException(String message) {
        super(message);
    }
}
