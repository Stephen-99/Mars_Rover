package edu.curtin.comp2003.rover.Controllers;

import java.util.ArrayList;

import edu.curtin.comp2003.rover.API.*;
import edu.curtin.comp2003.rover.Events.*;
import edu.curtin.comp2003.rover.Observers.*;
import edu.curtin.comp2003.rover.States.*;

//Responsible for creating all the objects and setting 
//up relationships between them
public class Injector 
{
    public static EventLoop injectAll()
    {
        //Creating all event objects
        //CommsEvents
        PollCommandEvent pollCommandEvent = new PollCommandEvent();
        SendMessageEvent sendMessageEvent = new SendMessageEvent();

        //StateEvents
        DistanceDrivenEvent distanceDrivenEvent = new DistanceDrivenEvent();
        NewCommandEvent newCommandEvent = new NewCommandEvent();
        NewVisibilityEvent newVisibilityEvent = new NewVisibilityEvent();
        SoilAnalysisFinishedEvent soilAnalysisFinishedEvent = new SoilAnalysisFinishedEvent();

        //RoverEvents
        CheckEnvironmentEvent checkEnvironmentEvent = new CheckEnvironmentEvent();
        GetDistanceEvent getDistanceEvent = new GetDistanceEvent();
        PhotoEvent photoEvent = new PhotoEvent();
        PollSoilEvent pollSoilEvent = new PollSoilEvent();
        StartDrivingEvent startDrivingEvent = new StartDrivingEvent();
        StartSoilEvent startSoilEvent = new StartSoilEvent();
        StopDrivingEvent stopDrivingEvent = new StopDrivingEvent();
        TurnEvent turnEvent = new TurnEvent();
        VisibilityEvent visibilityEvent = new VisibilityEvent();

        //States
        Idle idle = new Idle(checkEnvironmentEvent, pollCommandEvent, sendMessageEvent, 
            turnEvent, photoEvent, startSoilEvent, visibilityEvent);
        Driving driving = new Driving(getDistanceEvent, pollCommandEvent, sendMessageEvent,
            turnEvent, photoEvent, startSoilEvent, checkEnvironmentEvent, stopDrivingEvent, 
            visibilityEvent);
        AnalyzingSoil analyzingSoil = new AnalyzingSoil(checkEnvironmentEvent, pollCommandEvent, 
            sendMessageEvent, turnEvent, photoEvent, startSoilEvent, pollSoilEvent, visibilityEvent);

        //API
        EarthComm earthComm = new EarthComm();
        EngineSystem engineSystem = new EngineSystem();
        Sensors sensors = new Sensors();
        SoilAnalyser soilAnalyser = new SoilAnalyser();

        //Observers
        StateObserver stateObserver = new StateObserver();

        ArrayList<StateObserver> stateObs = new ArrayList<StateObserver>();
        stateObs.add(stateObserver);

        CommsObserver commsObserver = new CommsObserver(earthComm, stateObs, newCommandEvent);

        ArrayList<CommsObserver> commsObs = new ArrayList<CommsObserver>();
        commsObs.add(commsObserver);

        EngineObserver engineObserver = new EngineObserver(stateObs, commsObs, engineSystem, 
            distanceDrivenEvent);
        SensorsObserver sensorsObserver = new SensorsObserver(sensors, sendMessageEvent, 
            stateObs, commsObs, newVisibilityEvent);
        SoilObserver soilObserver = new SoilObserver(stateObs, commsObs, soilAnalyser, 
            soilAnalysisFinishedEvent, sendMessageEvent);

        ArrayList<RoverObserver> roverObs = new ArrayList<RoverObserver>();
        roverObs.add(engineObserver);
        roverObs.add(sensorsObserver);
        roverObs.add(soilObserver);

        //StateController
        //ASSUME starts in idle state
        double initialVisibility = sensors.readVisibility();
        StateController stateController = new StateController(idle, driving, analyzingSoil, 
            idle, startSoilEvent, startDrivingEvent, checkEnvironmentEvent, initialVisibility,
            commsObs, roverObs);

        //Initialise objects useing stateController
        stateObserver.initialiseController(stateController);
        idle.initialiseContext(stateController);
        driving.initialiseContext(stateController);
        analyzingSoil.initialiseContext(stateController);

        return new EventLoop(stateController);
    }
}