package org.example.Flight.FlightExceptions;

public class FlightNotFoundException extends RuntimeException {
    public FlightNotFoundException(String message) {
        super(message);
    }
    public FlightNotFoundException(String message, Throwable cause){
        super(message, cause);
    }
}