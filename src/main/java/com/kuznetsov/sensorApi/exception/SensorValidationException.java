package com.kuznetsov.sensorApi.exception;

public class SensorValidationException extends RuntimeException{
    public SensorValidationException(String message) {
        super(message);
    }
}
