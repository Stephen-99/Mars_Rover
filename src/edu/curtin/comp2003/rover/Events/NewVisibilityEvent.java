package edu.curtin.comp2003.rover.Events;

import edu.curtin.comp2003.rover.Observers.StateObserver;

public class NewVisibilityEvent implements StateEvent 
{
    private Double visibility;

    public NewVisibilityEvent()
    {
        visibility = null;
    }

    public void setVisibility(double visibility)
    {
        this.visibility = visibility;
    }

    @Override
    public void actOnEvent(StateObserver obs) {
        obs.visibilityUpdate(visibility);
    }
    
}
