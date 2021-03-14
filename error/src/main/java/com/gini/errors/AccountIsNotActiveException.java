package com.gini.errors;

public class AccountIsNotActiveException extends RuntimeException{

    public AccountIsNotActiveException(String message) {
        super(message);
    }
}
