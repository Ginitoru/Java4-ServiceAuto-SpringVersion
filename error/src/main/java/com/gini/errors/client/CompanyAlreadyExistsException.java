package com.gini.errors.client;

public class CompanyAlreadyExistsException extends RuntimeException{

    public CompanyAlreadyExistsException(String message) {
        super(message);
    }
}
