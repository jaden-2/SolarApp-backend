package com.jaden_2.solar.backend.exceptions;

public class ChargeControllerNotFoundException extends RuntimeException {
    public ChargeControllerNotFoundException(String message) {
        super(message);
    }
    public ChargeControllerNotFoundException(){
        super();
    }
}
