package com.ap.behavioral.Command;

/*the Command for turning on the light*/

public class TurnOnLightCommand implements Command{
   private Light theLight;
 
   public TurnOnLightCommand(Light light){
        this.theLight=light;
       }
 
   public void execute(){
      theLight.turnOn();
   }
}
