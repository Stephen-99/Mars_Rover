package edu.curtin.comp2003.rover.API;

public class EngineSystem 
{
    private int dd;
    private double distance;
    private double[] distTrav = new double[]{0.0, 3.0, 5.0, 18.0, 24.0};

    public EngineSystem()
    {
        dd = 0;
        distance = 0;
    }

    public void startDriving() 
    {
        System.out.println("ENGINE: starting to drive");
    }

    public void stopDriving()
    {
        System.out.println("ENGINE: Stopped driving");
    }

    public void turn (double angle)
    {
        System.out.println("ENGINE: Turning " + angle + " degreees");
    }

    public double getDistanceDriven()
    {
        
        if (dd < 5)
        {
            distance = distTrav[dd];
            dd++;
        }
        return distance;
    }
}
