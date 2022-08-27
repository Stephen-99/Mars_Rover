package edu.curtin.comp2003.rover.Events;

import edu.curtin.comp2003.rover.Observers.RoverObserver;

public class CheckEnvironmentEvent implements RoverEvent
{
    @Override
    public void actOnEvent(RoverObserver obs) {
        obs.checkEnvironment();
    }
    
}
