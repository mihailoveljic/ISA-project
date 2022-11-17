package eu.dreamTeam.isabackend.handler.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class FailedUpdateException extends RuntimeException {

    public FailedUpdateException() {
        super();
    }
}
