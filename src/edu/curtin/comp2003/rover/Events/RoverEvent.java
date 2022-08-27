package edu.curtin.comp2003.rover.Events;

import edu.curtin.comp2003.rover.Observers.RoverObserver;

public interface RoverEvent extends Event<RoverObserver>
{
    /*Groups together events that are Rover events --> that involve 
        the rover physically Doing something
        This meand it can only use roverObservers as enforced by the generic
    */
}
