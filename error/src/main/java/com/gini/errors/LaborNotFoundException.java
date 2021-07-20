package com.gini.errors;

public class LaborNotFoundException extends RuntimeException{
    public LaborNotFoundException(String message) {
        super(message);
    }
}
