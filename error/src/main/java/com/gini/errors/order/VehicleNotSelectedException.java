package com.gini.errors.order;

public class VehicleNotSelectedException extends RuntimeException{
    public VehicleNotSelectedException() {
    }

    public VehicleNotSelectedException(String message) {
        super(message);
    }
}
