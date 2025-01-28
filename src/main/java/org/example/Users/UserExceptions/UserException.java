package org.example.Users.UserExceptions;

public class UserException extends RuntimeException {
    public UserException(String message) {
        super(message);
    }

    public UserException(String message, Throwable cause){
      super(message, cause);
    }
}
