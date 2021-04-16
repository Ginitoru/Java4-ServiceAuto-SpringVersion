package com.gini.errors.order;

public class SelectOrderException extends RuntimeException{
    public SelectOrderException() {
    }

    public SelectOrderException(String message) {
        super(message);
    }
}
