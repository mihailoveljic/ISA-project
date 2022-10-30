package eu.dreamTeam.isabackend.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
@RestController
public class CustomizedExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(InvalidApiKeyException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public ErrorResponse handleInvalidApiKeyException(InvalidApiKeyException ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage("Invalid api key entered.");
        return errorResponse;
    }
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handleBadRequestException(Exception ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage("Error in format of entered data.");
        return errorResponse;
    }
}
