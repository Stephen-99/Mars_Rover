package edu.curtin.comp2003.rover.API;

public class SoilAnalyser 
{
    private int ii;
    private byte[] soil;
    private byte[][] soils = new byte[][]{null, null, {0, 12, 120, 117, 109, 123}, {0, 12, -123}};
    
    public void startAnalysis()
    {
        System.out.println("SOIL: started soil analysis");
    }

    public byte[] pollAnalysis()
    {
        if (ii < soils.length)
        {
            soil = soils[ii];
            ii++;
        }
        return soil;
    }
}
