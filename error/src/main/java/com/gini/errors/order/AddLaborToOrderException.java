package com.gini.errors.order;

public class AddLaborToOrderException  extends RuntimeException{
    public AddLaborToOrderException() {
    }

    public AddLaborToOrderException(String message) {
        super(message);
    }
}
