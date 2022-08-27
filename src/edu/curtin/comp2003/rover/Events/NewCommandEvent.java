package edu.curtin.comp2003.rover.Events;

import edu.curtin.comp2003.rover.Observers.StateObserver;

public class NewCommandEvent implements StateEvent
{
    private String command;

    public NewCommandEvent()
    {
        command = "No command set";
    }

    public void setCommand(String command)
    {
        this.command = command;
    }

    @Override
    public void actOnEvent(StateObserver obs) 
    {
        obs.newCommand(command);
    }
    
}
