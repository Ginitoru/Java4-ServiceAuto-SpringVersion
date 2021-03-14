package com.gini.errors;

public class EmailIsNotRegisteredException extends RuntimeException{


    public EmailIsNotRegisteredException(String message) {
        super(message);
    }
}
