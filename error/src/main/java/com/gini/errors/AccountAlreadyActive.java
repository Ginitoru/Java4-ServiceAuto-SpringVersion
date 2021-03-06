package com.gini.errors;

public class AccountAlreadyActive extends RuntimeException{

    public AccountAlreadyActive(String message) {
        super(message);
    }
}
