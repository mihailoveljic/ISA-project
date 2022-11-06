package eu.dreamTeam.isabackend.handler;

import eu.dreamTeam.isabackend.handler.exceptions.InvalidApiKeyException;
import eu.dreamTeam.isabackend.handler.exceptions.InvalidCreateAppointmentDTOException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
@RestController
public class CustomizedExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(InvalidApiKeyException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ResponseBody
    public ErrorResponse handleInvalidApiKeyException(InvalidApiKeyException ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage("Invalid api key entered.");
        return errorResponse;
    }

    @ExceptionHandler(InvalidCreateAppointmentDTOException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handleInvalidCreateAppointmentDTOException(InvalidCreateAppointmentDTOException ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage("Some of input fields are not valid!");
        return errorResponse;
    }
}
