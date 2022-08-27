package edu.curtin.comp2003.rover.Observers;

import java.util.Base64;
import java.util.List;

import edu.curtin.comp2003.rover.API.SoilAnalyser;
import edu.curtin.comp2003.rover.Events.SendMessageEvent;
import edu.curtin.comp2003.rover.Events.SoilAnalysisFinishedEvent;

/*Looks after all communication with the SoilAnalyser */
public class SoilObserver extends RoverObserver
{
    private SoilAnalyser soilAnalyser;
    private SoilAnalysisFinishedEvent analysisFinished;
    private SendMessageEvent sendMsgEvent;
    
    public SoilObserver(List<StateObserver> stateObs, List<CommsObserver> commsObs,
        SoilAnalyser soilAnalyser, SoilAnalysisFinishedEvent analysisFinished, 
        SendMessageEvent sendMsgEvent) 
    {
        super(stateObs, commsObs);
        this.soilAnalyser = soilAnalyser;
        this.analysisFinished = analysisFinished;
        this.sendMsgEvent = sendMsgEvent;
    }

    @Override
    public void startSoilAnalysis()
    {
        soilAnalyser.startAnalysis();
    }

    @Override
    public void pollSoilAnalysis()
    {
        byte[] results = soilAnalyser.pollAnalysis();
        if (results != null)
        {
            String soilResult = Base64.getEncoder().encodeToString(results);
            sendMsgEvent.setMessage("S " + soilResult);
            
            notifyCommsObservers(sendMsgEvent);
            notifyStateObservers(analysisFinished);
        }
    }
}
