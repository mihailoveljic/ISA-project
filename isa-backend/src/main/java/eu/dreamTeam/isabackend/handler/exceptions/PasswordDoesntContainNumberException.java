package eu.dreamTeam.isabackend.handler.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PasswordDoesntContainNumberException extends RuntimeException {

    public PasswordDoesntContainNumberException() {
        super();
    }
}
