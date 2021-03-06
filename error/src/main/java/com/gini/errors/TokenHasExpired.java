package com.gini.errors;

public class TokenHasExpired extends RuntimeException{

    public TokenHasExpired(String message) {
        super(message);
    }
}
