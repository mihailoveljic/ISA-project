package eu.dreamTeam.isabackend.handler.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BankNotExistedException extends RuntimeException {

    public BankNotExistedException() {
        super();
    }
}