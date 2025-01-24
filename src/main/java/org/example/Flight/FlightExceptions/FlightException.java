package org.example.Flight.FlightExceptions;

public class FlightException extends RuntimeException {
    public FlightException(String message) {
        super(message);
    }

    public FlightException(String message, Throwable cause){
        super(message, cause);
    }
}