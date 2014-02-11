package com.ap.behavioral.Command;

/*the Invoker class*/

public class Switch {
    private Command flipUpCommand;
    private Command flipDownCommand;
 
    public Switch(Command flipUpCmd,Command flipDownCmd){
         this.flipUpCommand=flipUpCmd;
         this.flipDownCommand=flipDownCmd;
    }
 
    public void flipUp(){
         flipUpCommand.execute();
    }
 
    public void flipDown(){
         flipDownCommand.execute();
    }
}
