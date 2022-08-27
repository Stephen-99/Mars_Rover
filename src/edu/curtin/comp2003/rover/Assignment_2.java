package edu.curtin.comp2003.rover;

import edu.curtin.comp2003.rover.Controllers.EventLoop;
import edu.curtin.comp2003.rover.Controllers.Injector;

/*Starting point of the application. Calls injector and has 
emergency error handling*/
public class Assignment_2 
{
    public static void main(String[] args) 
    {
        try {
            EventLoop loop = Injector.injectAll();
            loop.startLoop();
        } catch (Exception e) {
            System.out.println("An unexpected problem with the " +
            "program occurred");
            e.printStackTrace();
        }
        
    }
}
