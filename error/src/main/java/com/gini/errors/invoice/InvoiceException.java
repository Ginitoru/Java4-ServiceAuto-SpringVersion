package com.gini.errors.invoice;

public class InvoiceException extends RuntimeException{

    public InvoiceException() {
    }

    public InvoiceException(String message) {
        super(message);
    }
}
