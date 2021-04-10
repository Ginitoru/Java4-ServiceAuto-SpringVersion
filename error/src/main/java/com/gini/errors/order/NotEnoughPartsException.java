package com.gini.errors.order;

public class NotEnoughPartsException extends RuntimeException{
    public NotEnoughPartsException() {
    }

    public NotEnoughPartsException(String message) {
        super(message);
    }
}
