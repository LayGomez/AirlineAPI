package org.example.Airport.airportExceptions;

public class AirportException extends RuntimeException {
    public AirportException(String message) {
        super(message);
    }

    public AirportException(String message, Throwable cause){
        super(message, cause);
    }
}