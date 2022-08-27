package edu.curtin.comp2003.rover.Observers;

import edu.curtin.comp2003.rover.Controllers.StateController;
import edu.curtin.comp2003.rover.Events.StateEvent;

/*Looks after communnication with the state. It communicates via 
the stateController*/
public class StateObserver extends Observer<StateEvent>
{
    private StateController controller;

    public StateObserver()
    {
        this.controller = null;
    }

    public void initialiseController(StateController controller)
    {
        this.controller = controller;
    }

    public void newDistanceTravelled(double distance)
    {
        controller.valueUpdated(distance);
    }

    public void newCommand(String command)
    {
        controller.newCommand(command);
    }

    public void soilSampleFinished()
    {
        controller.setStateI();
    }

    public void visibilityUpdate(double visibility)
    {
        controller.visibilityUpdate(visibility);
    }
}
