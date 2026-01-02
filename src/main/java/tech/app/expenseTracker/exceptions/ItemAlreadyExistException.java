package tech.app.expenseTracker.exceptions;

public class ItemAlreadyExistException extends RuntimeException{
    public ItemAlreadyExistException(String msg){
        super(msg);
    }
}
