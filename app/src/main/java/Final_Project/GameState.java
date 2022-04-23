/*
GameState.java
For use in the Final project for COSC 236.
Based on starter code first developed by Prof. Dastyni Loksa

This is the class to hold the state of the running game and allows easy
passing of important information to methods that require data from the
state of the game.

This starter code is designed for the verbs to be stored in the commandSystem.

*/

package Final_Project;
public class GameState {
    Location currentLocation;
    CommandSystem commandSystem;
    
    public static int DISPLAY_WIDTH = 80;

    /*
        GameState Constructor

        Ideally, your game will be fully loaded and ready to play once this constructor has finished running.

        How things have been done here are just a rudementry setup to link the other classes and have the 
        bare bones example of the command system. This is not a great way to initilize your project.

        You should do better!
    */
    public GameState(){
        commandSystem = new CommandSystem(this);
        
        // Create first (starting) location 
        // (and store it in currentLocation so I can always referece where the player is easily)
        currentLocation = new Location();
        currentLocation.name = "Front [porch]";
        currentLocation.description = "You are on the front pourch of the house. The red front [door] is waiting to be knocked on and there is a welcome [mat] at your feet.";

        // Create a demo item.
        Item mat = new Item();
        mat.name = "Mat";
        mat.description = "There is a welcome mat here. This is a special mat.";

        //Add item to list of nouns so our command system knows it exists.
        commandSystem.addNoun(mat.name);

        /* 
            Once the commandSystem knows about the item, we need to code what happens with each of the commands that can happen with the item.
            See CommandSystem line 64 for what happens if you currently "look mat"
        */
    }
}
