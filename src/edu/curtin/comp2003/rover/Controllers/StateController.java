package edu.curtin.comp2003.rover.Controllers;

import java.util.List;

import edu.curtin.comp2003.rover.Events.*;

import edu.curtin.comp2003.rover.Observers.CommsObserver;
import edu.curtin.comp2003.rover.Observers.RoverObserver;
import edu.curtin.comp2003.rover.States.*;

//controlls communication between state observers and rover state
//as well as visibility state.
//This is needed so that the observerrs of the state class can
//Be centralised and not repeated across all possible states
public class StateController
{
    private RoverState roverState;
    //Visibility is also a state, but is much simpler and so the state 
    //pattern is not needed
    private double visibility;

    private Driving driving;
    private AnalyzingSoil analzingSoil;
    private Idle idle;

    private List<CommsObserver> commsObs;
    private List<RoverObserver> roverObs;

    private StartSoilEvent startSoilEvent;
    private StartDrivingEvent startDrivingEvent;
    private CheckEnvironmentEvent checkEnvironmentEvent;
    
    public StateController(RoverState roverState, Driving d, AnalyzingSoil s, 
        Idle i, StartSoilEvent startSoilEvent, StartDrivingEvent startDrivingEvent,
        CheckEnvironmentEvent checkEnvironmentEvent, double visibility, 
        List<CommsObserver> commsObs, List<RoverObserver> roverObs)
    {
        this.roverState = roverState;
        driving = d;
        analzingSoil = s;
        idle = i;
        this.startSoilEvent = startSoilEvent;
        this.startDrivingEvent = startDrivingEvent;
        this.checkEnvironmentEvent = checkEnvironmentEvent;
        this.visibility = visibility;
        this.commsObs = commsObs;
        this.roverObs = roverObs;
    }

    //State
    //Commented out print statements are useful to help see whats going on
    public void setStateD(double targetDist)
    {
        notifyRoverObservers(startDrivingEvent);
        roverState = driving;
        //System.out.println("Set state to Driving");
        driving.startDriving(targetDist);
    }

    public void setStateI()
    {
        roverState = idle;
        //System.out.println("Set state to Idle");
    }

    public void setStateS()
    {
        notifyRoverObservers(startSoilEvent);
        roverState = analzingSoil;
        //System.out.println("Set state to Soil");
    }

    //wrapping some state methods so they don't need to get state 
    //in order to call the method
    public void valueUpdated(double value)
    {
        roverState.valueUpdated(value);
    }

    public void newCommand(String command)
    {
        roverState.validateCommand(command);
    }

    public void poll()
    {
        roverState.poll();
    }

    //Observers
    public void addCommsObserver(CommsObserver commsOb)
    {
        commsObs.add(commsOb);
    }

    public void removeCommsObserver(CommsObserver commsOb)
    {
        commsObs.remove(commsOb);
    }

    public void notifyCommsObservers(CommsEvent e)
    {
        for (CommsObserver obs : commsObs)
        {
            obs.eventOccurred(e);
        }
    }

    public void addRoverObserver(RoverObserver roverOb)
    {
        roverObs.add(roverOb);
    }

    public void removeRoverObserver(RoverObserver roverOb)
    {
        roverObs.remove(roverOb);
    }

    public void notifyRoverObservers(RoverEvent e)
    {
        for (RoverObserver obs : roverObs)
        {
            obs.eventOccurred(e);
        }
    }

    public void visibilityUpdate(double vis)
    {
        //If visibility moves below 4km or above 5km, send an environment message
        if ((visibility >= 4 && vis < 4) || (visibility <= 5 && vis > 5))
        {
            notifyRoverObservers(checkEnvironmentEvent);
        }
        //update to store the new visibility
        visibility = vis;
    }

}
