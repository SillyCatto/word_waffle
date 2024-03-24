package core;

public class NotEnoughLettersException extends Exception{
    public NotEnoughLettersException(String exceptionMsg){
        super(exceptionMsg);
    }
}
