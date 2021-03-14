package com.gini.errors;

public class TokenHasExpiredException extends RuntimeException{

    public TokenHasExpiredException(String message) {
        super(message);
    }
}
