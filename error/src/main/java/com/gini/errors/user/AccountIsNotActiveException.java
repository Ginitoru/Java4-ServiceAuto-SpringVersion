package com.gini.errors.user;

public class AccountIsNotActiveException extends RuntimeException{

    public AccountIsNotActiveException(String message) {
        super(message);
    }
}
