package com.gini.errors;

public class AccountIsNotActive extends RuntimeException{

    public AccountIsNotActive(String message) {
        super(message);
    }
}
