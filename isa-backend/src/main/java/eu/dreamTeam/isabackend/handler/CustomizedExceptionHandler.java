package eu.dreamTeam.isabackend.handler;

import eu.dreamTeam.isabackend.handler.exceptions.*;
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

    @ExceptionHandler(InvalidPasswordException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handleInvalidPasswordException(InvalidPasswordException ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage("The current password is entered incorrectly!");
        return errorResponse;
    }

    @ExceptionHandler(AccountNotExistedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public ErrorResponse handleAccountNotExistedException(AccountNotExistedException ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage("Account doesn't exist!");
        return errorResponse;
    }

    @ExceptionHandler(StaffNotExistedException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorResponse handleStaffNotExistedException(StaffNotExistedException ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage("Staff with given email doesn't exist!");
        return errorResponse;
    }
    @ExceptionHandler(BankNotExistedException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorResponse handleBankNotExistedException(BankNotExistedException ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage("Blood bank for staff with given email doesn't exist!");
        return errorResponse;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorResponse handleException(Exception ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage("Server encountered internal error.");
        return errorResponse;
    }
}
