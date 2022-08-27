package edu.curtin.comp2003.rover.Observers;

import edu.curtin.comp2003.rover.Events.Event;

/*Observers watch for events to occur and then ask the event to call 
The appropriate observer method to deal with that event
Can't parametize Event, create an infinite loop, where we need to
specify the observer type then the event type etc.*/
public abstract class Observer<T extends Event>
{
    public void eventOccurred(T event)
    {
        event.actOnEvent(this);
    }

}
