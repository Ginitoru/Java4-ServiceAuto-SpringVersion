package com.gini.errors.client;

public class PersonNotFoundException extends RuntimeException{


    public PersonNotFoundException() {
    }

    public PersonNotFoundException(String message) {
        super(message);
    }
}
