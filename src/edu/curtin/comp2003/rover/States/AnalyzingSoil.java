package edu.curtin.comp2003.rover.States;

import edu.curtin.comp2003.rover.Events.*;

/*State of the rover when analyzing the soil (ik shoulda spelt it with an s...)
Overrides the poll method in roverState to check if finished analysing soil */
public class AnalyzingSoil extends RoverState
{
    private PollSoilEvent pollSoilEvent;

    public AnalyzingSoil(CheckEnvironmentEvent environmentEvent,
    PollCommandEvent pollCommandEvent, SendMessageEvent sendMsgEvent,
    TurnEvent turnEvent, PhotoEvent photoEvent, StartSoilEvent startSoil,
    PollSoilEvent pollSoilEvent, VisibilityEvent visibilityEvent) 
    {
        super(pollCommandEvent, sendMsgEvent, turnEvent, photoEvent, 
            environmentEvent, startSoil, visibilityEvent);
        this.pollSoilEvent = pollSoilEvent;
    }

    @Override
    public void poll() {
        context.notifyRoverObservers(pollSoilEvent);
        super.poll();
    }

    public void validateDrive(String command) throws InvalidCommandException
    {
        //Cannot start driving while performing soil analysis
        throw new InvalidCommandException();
    }

    public void validateTurn(String command) throws InvalidCommandException
    {
        //Cannot turn while performing soil analysis
        throw new InvalidCommandException();
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
        //Cannot start soil analysis while performing soil analysis
        throw new InvalidCommandException();
    }
    
}
