package com.gini.errors.order;

public class ClientNotSelectedException extends RuntimeException{

    public ClientNotSelectedException() {
    }

    public ClientNotSelectedException(String message) {
        super(message);
    }
}
