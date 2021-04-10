package com.gini.errors.order;

public class SelectPartException extends RuntimeException{
    public SelectPartException() {
    }

    public SelectPartException(String message) {
        super(message);
    }
}
