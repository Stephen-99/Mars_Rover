package edu.curtin.comp2003.rover.Observers;

import java.util.List;

import edu.curtin.comp2003.rover.API.EarthComm;
import edu.curtin.comp2003.rover.Events.CommsEvent;
import edu.curtin.comp2003.rover.Events.NewCommandEvent;
import edu.curtin.comp2003.rover.Events.StateEvent;

/*Looks after all the communication with the earth comms class
    Has the only instance of Earth comms*/
public class CommsObserver extends Observer<CommsEvent>
{

    private EarthComm comms;
    private List<StateObserver> stateObs;
    private NewCommandEvent newCommand;

    public CommsObserver(EarthComm comms, List<StateObserver> stateObs,
        NewCommandEvent newCommand) 
    {
        this.comms = comms;
        this.stateObs = stateObs;
        this.newCommand = newCommand;
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
    
    public void pollCommand()
    {
        String command = comms.pollCommand();
        newCommand.setCommand(command);
        notifyStateObservers(newCommand);
    }

    public void sendMessage(String msg)
    {
        comms.sendMessage(msg);
    }
}
