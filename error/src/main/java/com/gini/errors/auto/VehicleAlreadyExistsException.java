package com.gini.errors.auto;

public class VehicleAlreadyExistsException extends RuntimeException{

    public VehicleAlreadyExistsException(String message) {
        super(message);
    }
}
