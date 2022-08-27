package edu.curtin.comp2003.rover.States;

import edu.curtin.comp2003.rover.Controllers.StateController;
import edu.curtin.comp2003.rover.Events.*;

/*State the Rover is in. Deals with polling and validating the 
commands. Sub-classes should implement these validation hook methods 
to match their state-specific needs*/
public abstract class RoverState
{
    protected StateController context;

    /*Need all these events since we shouldn't create new objects
    outside of a factory, so events need to be passed in...
    protected so sub-classes can use*/
    protected PollCommandEvent pollCommandEvent;
    protected SendMessageEvent sendMsgEvent;
    protected TurnEvent turnEvent;
    protected PhotoEvent photoEvent;
    protected CheckEnvironmentEvent environmentEvent;
    protected StartSoilEvent startSoilEvent;

    private VisibilityEvent visibilityEvent;

    protected RoverState(PollCommandEvent pollCommandEvent,
        SendMessageEvent sendMsgEvent, TurnEvent turnEvent, PhotoEvent photoEvent,
        CheckEnvironmentEvent environmentEvent, StartSoilEvent startSoilEvent,
        VisibilityEvent visibilityEvent)
    {
        this.context = null;
        this.pollCommandEvent = pollCommandEvent;
        this.sendMsgEvent = sendMsgEvent;
        this.turnEvent = turnEvent;
        this.photoEvent = photoEvent;
        this.environmentEvent = environmentEvent;
        this.startSoilEvent = startSoilEvent;
        this.visibilityEvent = visibilityEvent;
    }
    
    //Necassary sicne 2-way connection
    public void initialiseContext(StateController context)
    {
        this.context = context;
    }

    public void poll()
    {
        //Check visibility
        context.notifyRoverObservers(visibilityEvent);
        //check for new commands
        context.notifyCommsObservers(pollCommandEvent);
    }

    /*Currently only the driving class overrides this, therefore
    implemented to do nothing for the other states*/
    public void valueUpdated(double value)
    {

    }

    //The sub classes must validate these commands and act on them
    //if appropriate.
    public abstract void validateDrive(String command) throws InvalidCommandException;
    public abstract void validateTurn(String command) throws InvalidCommandException;
    public abstract void validatePhoto(String command) throws InvalidCommandException;
    public abstract void validateEnvironment(String command) throws InvalidCommandException;
    public abstract void validateSoil(String command) throws InvalidCommandException;

    public void validateCommand(String command)
    {
        try{
            if (command == null) {
                throw new InvalidCommandException();
            }
           switch (command.charAt(0))
           {
                case 'D':
                    validateDrive(command);
                    break;
                case 'T':
                    validateTurn(command);
                    break;
                case 'P':
                    validatePhoto(command);
                    break;
                case 'E':
                    validateEnvironment(command);
                    break;
                case 'S':
                    validateSoil(command);
                    break;
                default:
                    throw new InvalidCommandException();
           }
        
        //Catch if command invalid, or if first char doesn't exist(also invalid command)
        } catch (InvalidCommandException | IndexOutOfBoundsException e) {
            //Send an error message.
            sendMsgEvent.setMessage("! " + command);
            context.notifyCommsObservers(sendMsgEvent);
        }
    }

    //Checks command is in correct format where second arg is a double
    protected double validateDouble(String command) throws InvalidCommandException
    {
        Double value = null;
        try {
            if (command.charAt(1) != ' ')
            {
                throw new InvalidCommandException();
            }
            value = Double.parseDouble(command.split(" ")[1]);

        //Catch when not a double, or no space given
        } catch (IndexOutOfBoundsException | NullPointerException | 
            NumberFormatException e) {
            throw new InvalidCommandException();
        }
        return value;
    }

    //Centralising simple functionality that is used repeatedly
    protected void validateSingleCharCommand(String command) throws InvalidCommandException
    {
        if (command.length() > 1) {
            throw new InvalidCommandException();
        }
        /*Adeded for clarity not necessity. This method either does nothing
        Or throws an error to let calling code know the command was invalid*/
        return;
    }

}