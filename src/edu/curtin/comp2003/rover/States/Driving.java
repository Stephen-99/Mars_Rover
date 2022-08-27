package edu.curtin.comp2003.rover.States;

import edu.curtin.comp2003.rover.Events.*;

/*State of the rover when driving
Has to keep track of how far it needs to travel so it can check 
if it has finished driving*/
public class Driving extends RoverState
{
    //Desired distance is desired distance for the rover to travel
    private double desiredDistance;
    private Double initialDistance;

    
    private GetDistanceEvent getDistanceEvent;
    private StopDrivingEvent stopDrivingEvent;


    public Driving(GetDistanceEvent getDistanceEvent, 
        PollCommandEvent pollCommandEvent, SendMessageEvent sendMsgEvent,
        TurnEvent turnEvent, PhotoEvent photoEvent, StartSoilEvent startSoil,
        CheckEnvironmentEvent environmentEvent, StopDrivingEvent stopDrivingEvent,
        VisibilityEvent visibilityEvent) 
    {
        super(pollCommandEvent, sendMsgEvent, turnEvent, photoEvent,
            environmentEvent, startSoil, visibilityEvent);
        this.getDistanceEvent = getDistanceEvent;
        this.stopDrivingEvent = stopDrivingEvent;

        desiredDistance = 0.0;
        initialDistance = 0.0;
    }

    @Override
    public void poll() 
    {
        /*Will get the current distance travelled, and notify 
        The stateObserver which will call the valueUpdated 
        method below*/
        context.notifyRoverObservers(getDistanceEvent);
        super.poll();
    }

    public void setDesiredDistance(double dist)
    {
        desiredDistance = dist;
    }

    public void setInitialDistance(Double dist)
    {
        initialDistance = dist;
    }

    @Override
    public void valueUpdated(double dist)
    {
        if (initialDistance == null)
        {
            //need to set initial distance
            setInitialDistance(dist);
        } else {
            //Need to check if the rover has travelled for enough
            checkIfArrived(dist);
        }
    }

    public void checkIfArrived(double dist)
    {
        if (dist-initialDistance >= desiredDistance) {
            context.notifyRoverObservers(stopDrivingEvent);

            //Send a message to indicate we've stopped driving
            sendMsgEvent.setMessage("D");
            context.notifyCommsObservers(sendMsgEvent);

            //Move to idle state
            context.setStateI();
        }
    }

    public void startDriving(double dist)
    {
        setDesiredDistance(dist);
        /*Set to null, so when recieving distance, we know it is in order 
        to set the initial distance*/
        setInitialDistance(null);
        context.notifyRoverObservers(getDistanceEvent);
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
        startDriving(dist);
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
        //Cannot perform soil analysis when driving
        throw new InvalidCommandException();
    }

}
