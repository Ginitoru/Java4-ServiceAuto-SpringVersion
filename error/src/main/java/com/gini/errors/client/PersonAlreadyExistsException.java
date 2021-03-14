package com.gini.errors.client;

public class PersonAlreadyExistsException extends RuntimeException{

    public PersonAlreadyExistsException(String message) {
        super(message);
    }
}
