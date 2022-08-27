package edu.curtin.comp2003.rover.Observers;

import edu.curtin.comp2003.rover.API.Sensors;
import edu.curtin.comp2003.rover.Events.NewVisibilityEvent;
import edu.curtin.comp2003.rover.Events.SendMessageEvent;

import java.util.Base64;
import java.util.List;

/*Looks after all communication with the sensors*/
public class SensorsObserver extends RoverObserver
{
    private Sensors sensors;
    private SendMessageEvent sendMsgEvent;
    private NewVisibilityEvent visibilityEvent;

    public SensorsObserver(Sensors sensors, SendMessageEvent sme, 
        List<StateObserver> stateObs, List<CommsObserver> commsObs,
        NewVisibilityEvent visibilityEvent)
    {
        super(stateObs, commsObs);
        this.sensors = sensors;
        sendMsgEvent = sme;
        this.visibilityEvent = visibilityEvent;
    }
    
    @Override
    public void takePhoto() {
        byte[] photo = sensors.takePhoto();
        String photoBase64 = Base64.getEncoder().encodeToString(photo);
        
        sendMsgEvent.setMessage("P " + photoBase64);
        notifyCommsObservers(sendMsgEvent);
    }

    @Override
    public void checkEnvironment() {
        String envMsg = "E ";
        envMsg += sensors.readTemperature() + " ";
        envMsg += sensors.readVisibility() + " ";
        envMsg += sensors.readLightLevel();

        sendMsgEvent.setMessage(envMsg);
        notifyCommsObservers(sendMsgEvent);
    }

    @Override
    public void checkVisibility() {
        double vis = sensors.readVisibility();
        visibilityEvent.setVisibility(vis);
        notifyStateObservers(visibilityEvent);
    }
}
