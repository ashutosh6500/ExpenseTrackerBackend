package tech.app.expenseTracker.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import tech.app.expenseTracker.helpers.ErrorObject;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorObject> handleExpenseNotFoundException(ResourceNotFoundException ex, WebRequest request){
        ErrorObject errorObject = new ErrorObject();
        errorObject.setStatusCode(HttpStatus.NOT_FOUND.value());
        errorObject.setMsg(ex.getMessage());
        errorObject.setTimeStamp(new Date());
        return new ResponseEntity<ErrorObject>(errorObject, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorObject> handleMethodArgMismatchException(MethodArgumentTypeMismatchException ex,WebRequest webRequest){
        ErrorObject errorObject = new ErrorObject();
        errorObject.setTimeStamp(new Date());
        errorObject.setMsg(ex.getMessage());
        errorObject.setStatusCode(HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<ErrorObject>(errorObject,HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(ItemAlreadyExistException.class)
    public ResponseEntity<ErrorObject> handleItemExistException(ItemAlreadyExistException ex,WebRequest webRequest){
        ErrorObject errorObject = new ErrorObject();
        errorObject.setTimeStamp(new Date());
        errorObject.setMsg(ex.getMessage());
        errorObject.setStatusCode(HttpStatus.CONFLICT.value());
        return new ResponseEntity<ErrorObject>(errorObject,HttpStatus.CONFLICT);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorObject> handleExceptions(Exception ex,WebRequest webRequest){
        ErrorObject errorObject = new ErrorObject();
        errorObject.setTimeStamp(new Date());
        errorObject.setMsg(ex.getMessage());
        errorObject.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity<ErrorObject>(errorObject,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
