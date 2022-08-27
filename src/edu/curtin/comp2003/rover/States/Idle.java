package edu.curtin.comp2003.rover.States;

import edu.curtin.comp2003.rover.Events.*;

/*Represents the idle state of the rover, when it is stopped, not moving*/
public class Idle extends RoverState
{

    public Idle(CheckEnvironmentEvent environmentEvent,
    PollCommandEvent pollCommandEvent, SendMessageEvent sendMsgEvent,
    TurnEvent turnEvent, PhotoEvent photoEvent, StartSoilEvent startSoil, 
    VisibilityEvent visibilityEvent) 
    {
        super(pollCommandEvent, sendMsgEvent, turnEvent, photoEvent, 
            environmentEvent, startSoil, visibilityEvent);
    }
    

    public void validateDrive(String command) throws InvalidCommandException
    {
        double dist = validateDouble(command);
        if (dist < 0)
        {
            //Distance must be positive. 0 is ok, basically says
            //Stop driving
            throw new InvalidCommandException();
        }
        context.setStateD(dist);
    }

    public void validateTurn(String command) throws InvalidCommandException
    {
        double angle = validateDouble(command);
        if (angle < -180 || angle > 180)
        {
            //Angle must be between -180 and 180 degrees
            throw new InvalidCommandException();
        }
        turnEvent.setAngle(angle);
        context.notifyRoverObservers(turnEvent);
    }

    public void validatePhoto(String command) throws InvalidCommandException
    {
        //Will throw an exception if more than 1 char
        validateSingleCharCommand(command);

        context.notifyRoverObservers(photoEvent);
    }

    public void validateEnvironment(String command) throws InvalidCommandException
    {
        //Will throw an exception if more than 1 char
        validateSingleCharCommand(command);
        
        context.notifyRoverObservers(environmentEvent);
    }

    public void validateSoil(String command) throws InvalidCommandException
    {
        //Will throw an exception if more than 1 char
        validateSingleCharCommand(command);
        
        context.setStateS();
    }
    
}
