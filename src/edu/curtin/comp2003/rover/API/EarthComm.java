package edu.curtin.comp2003.rover.API;

public class EarthComm {

    private int ii;
    private int length;
    private String[] commands = new String[]{"", null, "D 5", "P", "E", "S", "T -175", "T -180.01", "P", "T -175", "T -180.01", "S", "E"};
    private String command;

    public EarthComm()
    {
        ii = 0;
        length = commands.length;
    }

    public String pollCommand() 
    {
        if (ii < length)
        {
            command = commands[ii];
            ii++;
        }
        System.out.println("Sending command: " + command);
        return command;
    }

    public void sendMessage(String msg)
    {
        System.out.println(msg + "\n");
    }
}
