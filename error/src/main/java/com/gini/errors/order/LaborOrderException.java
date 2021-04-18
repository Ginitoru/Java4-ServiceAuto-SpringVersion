package com.gini.errors.order;

public class LaborOrderException extends RuntimeException{
    public LaborOrderException() {
    }

    public LaborOrderException(String message) {
        super(message);
    }
}
