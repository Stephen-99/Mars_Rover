package edu.curtin.comp2003.rover.Observers;

import java.util.List;

import edu.curtin.comp2003.rover.Events.StateEvent;
import edu.curtin.comp2003.rover.Events.CommsEvent;
import edu.curtin.comp2003.rover.Events.RoverEvent;

/*Class takes care of any RoverEvents
Abstract because it doesn't make sense to create it on its own*/
public abstract class RoverObserver extends Observer<RoverEvent>
{
    private List<StateObserver> stateObs;
    private List<CommsObserver> commsObs;

    protected RoverObserver(List<StateObserver> stateObs, List<CommsObserver> commsObs)
    {
        this.stateObs = stateObs;
        this.commsObs = commsObs;
    }

    public void addStateObserver(StateObserver stateOb)
    {
        stateObs.add(stateOb);
    }

    public void removeStateObserver(StateObserver stateOb)
    {
        stateObs.remove(stateOb);
    }
    
    public void notifyStateObservers(StateEvent e)
    {
        for (StateObserver obs : stateObs)
        {
            obs.eventOccurred(e);
        }
    }

    public void addCommsObserver(CommsObserver CommsOb)
    {
        commsObs.add(CommsOb);
    }

    public void removeCommsObserver(CommsObserver CommsOb)
    {
        commsObs.remove(CommsOb);
    }

    public void notifyCommsObservers(CommsEvent e)
    {
        for (CommsObserver obs : commsObs)
        {
            obs.eventOccurred(e);
        }
    }

    //Implements all the methods to do nothing. Child classes 
    //can override these methods to match their responsibility
    public void checkVisibility()
    {

    }

    public void checkEnvironment()
    {

    }

    public void takePhoto()
    {

    }

    public void startDriving()
    {

    }

    public void stopDriving()
    {

    }

    public void turn(double angle)
    {

    }

    public void getDistanceDriven()
    {

    }

    public void startSoilAnalysis()
    {

    }

    public void pollSoilAnalysis()
    {

    }
}
