package tech.app.expenseTracker.exceptions;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String msg){
        super(msg);
    }
}
