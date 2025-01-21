package org.example.Country.CountryExceptions;

public class CountryException extends RuntimeException {
    public CountryException(String message) {
        super(message);
    }
    public CountryException(String message, Throwable cause){
        super(message, cause);
    }
}
