package edu.curtin.comp2003.rover.Events;

import edu.curtin.comp2003.rover.Observers.CommsObserver;

public interface CommsEvent extends Event<CommsObserver>
{
    /*groups together events that are comms events
    
    The actOnEvent method should only use CommsObservers
    However, I cannot override the method in the Event interface
    To only accept CommsObserver parameters since overridden
    methods have to ahve the same parameter types...
    I can (and have) enforced this using gnereics :D*/


}
