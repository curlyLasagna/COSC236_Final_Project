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
import com.google.common.graph.*;

public class GameState {
    CommandSystem commandSystem;
    MutableValueGraph<Location, Integer> locations 
      = ValueGraphBuilder.undirected().allowsSelfLoops(true).build();
    
    public static int DISPLAY_WIDTH = 80;

    /*
        GameState Constructor

        Ideally, your game will be fully loaded and ready to play once this constructor has finished running.

        How things have been done here are just a rudementry setup to link the other classes and have the 
        bare bones example of the command system. This is not a great way to initilize your project.

        You should do better!
    */
    public GameState() {
        commandSystem = new CommandSystem(this);

        // Location nodes
        Location 
          river, convenienceStore, startingPoint,
          campFire, fallenTree, home, opening, roadFork;

        // this line longer than chick fil a
        river = convenienceStore = startingPoint = campFire = fallenTree = home = opening = roadFork = new Location();

        // Add Location object as a node in the graph
        locations.addNode(river);       
        locations.addNode(convenienceStore);       
        locations.addNode(startingPoint);       
        locations.addNode(campFire);       
        locations.addNode(fallenTree);       
        locations.addNode(home);       
        locations.addNode(opening);       
        locations.addNode(roadFork);

        // Edge values represent how much toxicity is taxed when traversed 
        locations.putEdgeValue(startingPoint, river, 5);
        locations.putEdgeValue(startingPoint, fallenTree, 3);
        locations.putEdgeValue(startingPoint, roadFork, 10);
        locations.putEdgeValue(fallenTree, opening, 2);
        locations.putEdgeValue(fallenTree, home, 14);
        locations.putEdgeValue(roadFork, home, 6);
        locations.putEdgeValue(roadFork, convenienceStore, 12);
        locations.putEdgeValue(opening, campFire, 5);

        System.out.println(locations);

        // Init player items
        Lighter lighter = new Lighter();
        Stick stick = new Stick();
        Wallet wallet = new Wallet();

        //Add item to list of nouns so our command system knows it exists.
        // commandSystem.addNoun(lighter.getName());
        // commandSystem.addNoun(stick.getName());
        // commandSystem.addNoun(wallet.getName());

        /* 
            Once the commandSystem knows about the item, we need to code what happens with each of the commands that can happen with the item.
            See CommandSystem line 64 for what happens if you currently "look mat"
        */
    }
}
