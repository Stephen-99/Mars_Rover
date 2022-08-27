package edu.curtin.comp2003.rover.Observers;

import java.util.List;

import edu.curtin.comp2003.rover.API.EngineSystem;
import edu.curtin.comp2003.rover.Events.DistanceDrivenEvent;

/*Looks after all communication with the EngineSystem*/
public class EngineObserver extends RoverObserver
{
    private EngineSystem engine;
    private DistanceDrivenEvent distEvent;

    public EngineObserver(List<StateObserver> stateObs, List<CommsObserver> commsObs,
        EngineSystem engine, DistanceDrivenEvent distEvent) 
    {
        super(stateObs, commsObs);
        this.engine = engine;
        this.distEvent = distEvent;
    }
    
    @Override
    public void startDriving()
    {
        engine.startDriving();
    }

    @Override
    public void stopDriving()
    {
        engine.stopDriving();
    }

    @Override
    public void turn(double angle)
    {
        engine.turn(angle);
    }

    @Override
    public void getDistanceDriven()
    {
        double dist = engine.getDistanceDriven();
        distEvent.setDistance(dist);
        notifyStateObservers(distEvent);
    }
}
