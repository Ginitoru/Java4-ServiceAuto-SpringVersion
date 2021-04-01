package com.gini.errors.user;

public class TokenHasExpiredException extends RuntimeException{

    public TokenHasExpiredException(String message) {
        super(message);
    }
}
