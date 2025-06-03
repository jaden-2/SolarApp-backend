package com.jaden_2.solar.backend.exceptions;

public class BatteryTypeAndCapacityNotFoundException extends RuntimeException {
    public BatteryTypeAndCapacityNotFoundException(String message) {
        super(message);
    }
}
