package org.example.Users.UserExceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Username already exists")
public class UsernameAlreadyExistsException extends UserException{
    public UsernameAlreadyExistsException(String message){
        super(message);
    }
    public UsernameAlreadyExistsException(String message, Throwable cause){
        super(message, cause);
    }
}
