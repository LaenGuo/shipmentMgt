package com.cienet.shipment.exception;

public class ShipmentException extends RuntimeException {
    private Integer errorCode;
    private final String message;

    public ShipmentException(String message) {
        super(message);
        this.message = message;
    }

    public ShipmentException(Integer errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
        this.message = message;
    }
}