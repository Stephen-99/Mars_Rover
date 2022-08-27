package edu.curtin.comp2003.rover.API;

public class Sensors 
{
    private int tt, vv, ll, pp;
    private double temp, vis, light;
    private byte[] photo;
    private byte[][] photos = new byte[][]{{0, 12, 120, 117, 109, 123}, {0, 12, -123}};
    private Double[] temps = new Double[]{0.0, 12.0, 13.0, 4.5, 4.5, 62.4, 3.0, 1002.4};
    private Double[] visibilities = new Double[]{0.0, 12.0, 13.0, 4.5, 12.4, 12.1, 1002.4};
    private Double[] lights = new Double[]{0.0, 12.0, 13.0, 4.5, 62.4, 1002.4};

    public double readTemperature()
    {
        if (tt < temps.length)
        {
            temp = temps[tt];
            tt++;
        }
        return temp;
    }

    public double readVisibility()
    {
        if (vv < visibilities.length)
        {
            vis = visibilities[vv];
            vv++;
        }
        return vis;
    }

    public double readLightLevel()
    {
        if (ll < lights.length)
        {
            light = lights[ll];
            ll++;
        }
        return light;
    }

    public byte[] takePhoto()
    {
        if (pp < photos.length)
        {
            photo = photos[pp];
            pp++;
        }
        return photo;
    }
}
