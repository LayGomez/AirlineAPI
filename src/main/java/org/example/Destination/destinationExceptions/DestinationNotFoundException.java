package org.example.Destination.destinationExceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Destination not found")
public class DestinationNotFoundException extends RuntimeException {
    public DestinationNotFoundException(String message){
        super(message);
    }
    public DestinationNotFoundException(String message, Throwable cause){
        super(message, cause);
    }
}
