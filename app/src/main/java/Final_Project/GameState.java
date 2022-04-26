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


        // Create Items:
        // Create a lighter item.
        Item lighter = new Item();
        lighter.name = "Lighter";
        lighter.description = "You have a white lighter";

        // Create a stick item
        Item stick = new Item();
        stick.name = "Stick";
        stick.description = "You have a stick that you picked up from the ground";

        // create a wallet item
        Item wallet = new Item();
        wallet.name = "Wallet";
        wallet.description = "You reach in your pocket and find your wallet";
        
        //Add item to list of nouns so our command system knows it exists.
        commandSystem.addNoun(lighter.name);
        commandSystem.addNoun(stick.name);
        commandSystem.addNoun(wallet.name);

        /* 
            Once the commandSystem knows about the item, we need to code what happens with each of the commands that can happen with the item.
            See CommandSystem line 64 for what happens if you currently "look mat"
        */
    }
}
