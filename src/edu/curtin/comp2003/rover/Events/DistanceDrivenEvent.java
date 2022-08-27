package edu.curtin.comp2003.rover.Events;

import edu.curtin.comp2003.rover.Observers.StateObserver;

public class DistanceDrivenEvent implements StateEvent
{
    private double distance;

    public DistanceDrivenEvent()
    {
        //Initially the distance driven is 0
        distance = 0.0;
    }

    public void setDistance(double distance)
    {
        this.distance = distance;
    }

    @Override
    public void actOnEvent(StateObserver obs) 
    {
        obs.newDistanceTravelled(distance);
    }
    
}
