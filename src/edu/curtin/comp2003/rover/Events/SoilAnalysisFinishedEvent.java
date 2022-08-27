package edu.curtin.comp2003.rover.Events;

import edu.curtin.comp2003.rover.Observers.StateObserver;

public class SoilAnalysisFinishedEvent implements StateEvent
{
    @Override
    public void actOnEvent(StateObserver obs) 
    {
        obs.soilSampleFinished();
    }
    
}
