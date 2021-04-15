package com.gini.errors.labor;

public class InvalidPriceException extends RuntimeException{
    public InvalidPriceException() {
    }

    public InvalidPriceException(String message) {
        super(message);
    }
}
