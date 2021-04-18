package com.gini.errors.order;

public class PartOrderException extends RuntimeException{
    public PartOrderException() {
    }

    public PartOrderException(String message) {
        super(message);
    }
}
