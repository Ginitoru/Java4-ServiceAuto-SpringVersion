package com.gini.errors.user;

public class AccountAlreadyActiveException extends RuntimeException{

    public AccountAlreadyActiveException(String message) {
        super(message);
    }
}
