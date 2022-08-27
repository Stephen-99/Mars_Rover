package edu.curtin.comp2003.rover.Events;

import edu.curtin.comp2003.rover.Observers.Observer;

/*Represents an event that occurs. Events are ways of communicating
accross packages to allow de-coupling*/
public interface Event<T extends Observer>
{
    public void actOnEvent(T obs);
}
