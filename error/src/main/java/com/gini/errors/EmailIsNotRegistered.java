package com.gini.errors;

public class EmailIsNotRegistered extends RuntimeException{


    public EmailIsNotRegistered(String message) {
        super(message);
    }
}
