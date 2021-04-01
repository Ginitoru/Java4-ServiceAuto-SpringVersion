package com.gini.errors.user;

public class EmailIsNotRegisteredException extends RuntimeException{


    public EmailIsNotRegisteredException(String message) {
        super(message);
    }
}
