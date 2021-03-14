package com.gini.errors;

public class AccountAlreadyActiveException extends RuntimeException{

    public AccountAlreadyActiveException(String message) {
        super(message);
    }
}
