package edu.curtin.comp2003.rover.Events;

import edu.curtin.comp2003.rover.Observers.*;

public class PollCommandEvent implements CommsEvent
{
    
    @Override
    public void actOnEvent(CommsObserver obs) 
    {
        obs.pollCommand();
    }
    
}
