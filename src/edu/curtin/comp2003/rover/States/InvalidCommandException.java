package edu.curtin.comp2003.rover.States;

/*Used by the Roverstate class when the command provided
is an invalid Command*/
public class InvalidCommandException extends Exception {
    public InvalidCommandException(){
        super();
    }

    public InvalidCommandException(String message)
    {
        super(message);
    }

    public InvalidCommandException(String message, Throwable cause)
    {
        super(message, cause);
    }
}
