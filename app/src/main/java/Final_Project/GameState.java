/*
This is the class to hold the state of the running game and allows easy
passing of important information to methods that require data from the
state of the game.
*/
package Final_Project;
import java.util.HashMap;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;
import java.util.List;

import com.google.common.graph.*;

// import apple.laf.JRSUIConstants.Hit;


public class GameState {
    private CommandSystem commandSystem;
    private Player player;
    private MutableValueGraph<Location, Integer> locations 
      = ValueGraphBuilder.undirected().allowsSelfLoops(true).build();
    List <Location> locationList;
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
              This is where I last saw my friends. I need to find them
              There's a cliff to the north. I don't even want to try climbing up that
              A trail that lead towards the road in the south looks eerie
              A river to the east looks like a fun time
              A huge tree with no leaves 
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

        locationList = Arrays.asList(
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
                // Sets Normal entity in Opening
                new Normal()
            ), 
            new Location("Road", 
                "It's the main road. It's empty and dark")
            );

        // Add location as nodes in the graph
        locationList.forEach(locations::addNode);

        // HashMap<Map.Entry<Location, Location>, Integer> test = new HashMap<>(
        //    new Location("") 
        //     );

        String [] locationEdgeLocation = {
          "start:river:5",
          "start:tree:3",
          "start:road:10",
          "tree:opening:2",
          "tree:home:14",
          "road:home:6",
          "fork:7-11:12",
          "opening:camp fire:5"
        };

        HashMap<String, Integer> locationEdges = new HashMap<>();
        Map<String,Edge> all_edges = new TreeMap<String,Edge>();
        for(int i = 0; i < locationEdgeLocation.length; i++) {
          String [] split = locationEdgeLocation[i].split(":");
          String left = split[0];
          String right = split[1];
          String label = left + ":" + right;
          int edgeVal = Integer.parseInt(split[2]);
          Edge edge = new Edge(left, right, edgeVal);
          all_edges.put(label, edge);
          // locations.putEdgeValue();

          // locations.putEdgeValue(locationList.get(i).stringLocation().getValue());
        }


        // Edge values represent how much toxicity is taxed when traversed 
        locations.putEdgeValue(startingPoint, river, 5);
        locations.putEdgeValue(startingPoint, fallenTree, 3);
        locations.putEdgeValue(startingPoint, roadFork, 10);
        locations.putEdgeValue(fallenTree, opening, 2);
        locations.putEdgeValue(fallenTree, home, 14);
        locations.putEdgeValue(roadFork, home, 6);
        locations.putEdgeValue(roadFork, convenienceStore, 12);
        locations.putEdgeValue(opening, campFire, 5);

        // Spawn player at starting point
        player = new Player(startingPoint);

        // Add player items as nouns
        player.itemList.keySet().forEach(commandSystem::addNoun);

        // Adds all String key value of locations as a noun
        locations.nodes().forEach(l -> commandSystem.addNoun(l.stringLocation().getKey()));

        // Add entities
        locations.nodes().forEach(l -> commandSystem.addNoun(l.getEntityInLocation().getName()));

        // Adds commands
        commandSystem.addVerb("look", "look, look <item>, look <entity>");
        commandSystem.addVerb("walk", "walk <location>");
        commandSystem.addVerb("hit", "hit, hit <item> <entity>");
        commandSystem.addVerb("throw", "throw <item>");
        commandSystem.addVerb("light", "light <item>, light <item> <entity>");
        commandSystem.addVerb("use", "use <item> <entity>");

        // Add available commands to player.
        // player.itemActions.keySet().forEach(v -> 
        //     commandSystem.addVerb(v, player.itemActions.str + " [item]")
        //   );


        // commandSystem.addVerb("hit", "hit <entity>");
        // commandSystem.addVerb("throw", "throw <item> <entity>");

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

    CommandSystem getCommandSystem() {
      return commandSystem;
    }

}

class Edge {
public int value;
  public String left, right;
  public Edge(String left, String right, int value) {
    this.left = left;
    this.right = right;
    this.value = value;
  }
}
