/*
This is the class to hold the state of the running game and allows easy
passing of important information to methods that require data from the
state of the game.
*/
package Final_Project;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

import com.google.common.graph.*;

public class GameState {
    CommandSystem commandSystem;
    Player player;
    MutableValueGraph<Location, Integer> locations 
      = ValueGraphBuilder.undirected().allowsSelfLoops(true).build();
    
    public static int DISPLAY_WIDTH = 80;

    /*
        GameState Constructor
    */
    public GameState() {
        commandSystem = new CommandSystem(this);

        // Location nodes
        // This has got to go
        Location 
          river = 
            new Location("River", "Flows very nice", 
            new Scary()),

          convenienceStore = 
            new Location("7-11", "A convenience store? This is not okay. Why am I here?"), 

          startingPoint = 
            new Location (
              "Start", 
              """
              There's a cliff to the north. I don't even want to try climbing up that
              But I could go three ways. 
              There's nice trail if I go south. Looks enticing 
              """
            ),

          campFire = 
            new Location("Campfire", "You're in a campfire surrounded by your friends. You're safe now"), 

          fallenTree = 
            new Location("Tree", "You see a fallen tree. It's also a fork that leads north or south",
            new Tripy()), 

          home =
            new Location("Home", "You've reached home. You feel safe but alone."), 

          opening =
            new Location("Opening", "An opening field", 
            new Normal()), 

          roadFork = 
            new Location("Road", "It's the main road. It's empty and dark");

        Location [] locationArr = {
            new Location(
                "River", 
                "Flows very nice", 
            // Sets scary entity in River
            new Scary()
            ),
            new Location(
                "7-11", 
                "This is not okay. Why am I here?"), 
            new Location (
              "Starting point", 
              """
              There's a cliff to the north. I don't even want to try climbing up that
              """
            ),
            new Location(
                "Campfire", 
                "You're in a campfire surrounded by your friends. You're safe now"), 
            new Location(
                "Fallen tree", 
                "You see a fallen tree. It's also a fork that leads north or south",
                // Sets Tripy entity in Fallen Tree
                new Tripy()
            ), 
            new Location(
                "Home", 
                "You've reached home. You feel safe but alone."), 
            new Location(
                "Opening", 
                "An opening field", 
                new Normal()
            ), 
            new Location("Road", 
                "It's the main road. It's empty and dark")
        };
        

        // Add location as nodes in the graph
        for(Location l : locationArr)
          locations.addNode(l);

        HashMap<Entry<Location, Location>, Integer> locationEdges = new LinkedHashMap<>();

        // Edge values represent how much toxicity is taxed when traversed 
        locations.putEdgeValue(startingPoint, river, 5);
        locations.putEdgeValue(startingPoint, fallenTree, 3);
        locations.putEdgeValue(startingPoint, roadFork, 10);
        locations.putEdgeValue(fallenTree, opening, 2);
        locations.putEdgeValue(fallenTree, home, 14);
        locations.putEdgeValue(roadFork, home, 6);
        locations.putEdgeValue(roadFork, convenienceStore, 12);
        locations.putEdgeValue(opening, campFire, 5);

        // for (Location l : locations.adjacentNodes(startingPoint)) 
        //   System.out.println(l.getName());

        // Spawn player at starting point
        player = new Player(startingPoint);

        // Add player items as nouns
        for (Item i : player.getItemList())
          commandSystem.addNoun(i.getName());

        // Adds all locations as a noun
        for (Location l : locations.nodes())
          commandSystem.addNoun(l.stringLocation().getKey());

        /* 
            Once the commandSystem knows about the item, we need to code what happens with each of the commands that can happen with the item.
            See CommandSystem line 64 for what happens if you currently "look mat"
        */
    }

    Player getPlayer() {
      return player;
    }

    MutableValueGraph<Location, Integer> getLocations() {
      return locations;
    }

}
