package com.gini.errors.auto;

public class VehicleAlreadyExists extends RuntimeException{

    public VehicleAlreadyExists(String message) {
        super(message);
    }
}
