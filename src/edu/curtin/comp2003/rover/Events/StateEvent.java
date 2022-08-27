package edu.curtin.comp2003.rover.Events;

import edu.curtin.comp2003.rover.Observers.StateObserver;

public interface StateEvent extends Event<StateObserver>
{
    /*Groups together events that are state events --> events that could 
        result in a change of state
    Using the genric, forces implementations to use StateObservers*/
}
