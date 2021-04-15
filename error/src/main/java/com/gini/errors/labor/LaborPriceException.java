package com.gini.errors.labor;

public class LaborPriceException extends RuntimeException{

    public LaborPriceException() {
    }

    public LaborPriceException(String message) {
        super(message);
    }
}
