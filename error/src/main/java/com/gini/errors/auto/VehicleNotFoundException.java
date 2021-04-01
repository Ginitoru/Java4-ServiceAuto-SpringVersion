package com.gini.errors.auto;

public class VehicleNotFoundException extends RuntimeException{

    public VehicleNotFoundException() {
    }

    public VehicleNotFoundException(String message) {
        super(message);
    }
}
