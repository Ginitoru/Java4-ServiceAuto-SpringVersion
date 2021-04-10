package com.gini.errors.order;

public class BadIntegerNumberException extends RuntimeException{
    public BadIntegerNumberException() {
    }

    public BadIntegerNumberException(String message) {
        super(message);
    }
}
