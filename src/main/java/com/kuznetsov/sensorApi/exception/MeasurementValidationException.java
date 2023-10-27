package com.kuznetsov.sensorApi.exception;

public class MeasurementValidationException extends RuntimeException{
    public MeasurementValidationException(String message) {
        super(message);
    }
}
