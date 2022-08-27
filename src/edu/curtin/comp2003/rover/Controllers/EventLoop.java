package edu.curtin.comp2003.rover.Controllers;

//Main program loop, polls, sleeps reapeats :D
public class EventLoop 
{
    private StateController controller;
    
    public EventLoop(StateController controller)
    {
        this.controller = controller;
    }
    
    
    public void startLoop() 
    {
        while (true)
        {
            controller.poll();
            System.out.println("");

            try {
                //For testing purposes the commented line may be more ideal :D
                //Thread.sleep(1000);
                Thread.sleep(50);
            } catch (InterruptedException e) {
                System.out.println("Unexpected error occurred:");
                e.printStackTrace();
            }
        }
    }
}
