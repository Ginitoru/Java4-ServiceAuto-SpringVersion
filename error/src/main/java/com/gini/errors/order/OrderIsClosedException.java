package com.gini.errors.order;

public class OrderIsClosedException extends RuntimeException{
    public OrderIsClosedException() {
    }

    public OrderIsClosedException(String message) {
        super(message);
    }
}
