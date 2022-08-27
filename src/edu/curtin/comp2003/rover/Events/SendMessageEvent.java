package edu.curtin.comp2003.rover.Events;

import edu.curtin.comp2003.rover.Observers.CommsObserver;

public class SendMessageEvent implements CommsEvent 
{

    private String message;

    public SendMessageEvent()
    {
        message = "Message not set";
    }


    public void setMessage(String message)
    {
        this.message = message;
    }

    @Override
    public void actOnEvent(CommsObserver obs) 
    {
        obs.sendMessage(message);
    }
    
}
