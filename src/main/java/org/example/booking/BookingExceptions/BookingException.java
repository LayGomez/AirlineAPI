package org.example.booking.BookingExceptions;

public class BookingException extends RuntimeException {
    public BookingException(String message) {
        super(message);
    }
    public BookingException(String message, Throwable cause){
      super(message, cause);
    }
}
