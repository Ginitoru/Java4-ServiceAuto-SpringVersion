package com.gini.errors.order;

public class PartNotFoundException extends RuntimeException{
    public PartNotFoundException() {
    }

    public PartNotFoundException(String message) {
        super(message);
    }
}
