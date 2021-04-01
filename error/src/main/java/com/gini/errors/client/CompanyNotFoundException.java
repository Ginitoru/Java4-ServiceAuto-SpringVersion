package com.gini.errors.client;

public class CompanyNotFoundException extends RuntimeException{

    public CompanyNotFoundException() {
    }

    public CompanyNotFoundException(String message) {
        super(message);
    }
}
