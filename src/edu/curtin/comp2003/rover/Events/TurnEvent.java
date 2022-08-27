package edu.curtin.comp2003.rover.Events;

import edu.curtin.comp2003.rover.Observers.RoverObserver;

public class TurnEvent implements RoverEvent
{
    private double angle;

    public TurnEvent()
    {
        //If uninitialised, will not turn at all
        angle = 0.0;
    }

    public void setAngle(double angle)
    {
        this.angle = angle;
    }

    @Override
    public void actOnEvent(RoverObserver obs) 
    {
       obs.turn(angle);
    }
}
