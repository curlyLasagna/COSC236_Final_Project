/*
This is the class to hold the state of the running game and allows easy
passing of important information to methods that require data from the
state of the game.
*/
package Final_Project;
import java.util.HashMap;
import com.google.common.graph.*;

public class GameState {
    private CommandSystem commandSystem;
    private Player player;
    /* I could possibly set the weight as a string that would 
     * 
     */
    private MutableValueGraph<Location, Integer> locations 
      = ValueGraphBuilder.undirected().allowsSelfLoops(true).build();
    public HashMap <String, Location> locationList;
    public static int DISPLAY_WIDTH = 80;

    public GameState() {
        commandSystem = new CommandSystem(this);
        // Location nodes
        // Didn't have time to think of a better implementation
        locationList = new HashMap<>();
        // Create locations with their respective entities
        locationList.put("river", 
            new Location("River", "Flows very nice", 
              new Scary()));

        locationList.put("conbini", 
            new Location("Store", 
              "A convenience store? This is not okay. Why am I here?")); 

        locationList.put("start", 
            new Location("Start",               
              """
              This is where I last saw my friends. I need to find them
              There's a cliff to the north. I don't even want to try climbing up that
              A trail that lead towards the road in the south looks eerie
              A river to my right looks like a fun time
              A big scary tree  
              """
              ));

        locationList.put("tree", 
            new Location("Tree", 
              "You see a fallen tree. A majestic",
              new Tripy()
              ));

        locationList.put("campfire", 
            new Location("Campfire", 
              "You're in a campfire surrounded by your friends. You're safe now"
              ));

        locationList.put("home", 
            // Prompt the user if they want to go home
            new Location("Cabin", 
              // Eh em, Katie wtf 
              """
              You totally just realized it's your house.
              
              The door requires a swipe card to get in 
              Do you want to go in? 
              """
              ));

        locationList.put("road", 
            new Location("Road", 
              "It's a road. It's empty and dark"
              ));

        locationList.put("opening", 
            new Location("Opening", 
              """
              Empty field of beautiful flowing grass. 
              It almost looks like an ocean.
              I should go for a swim
              """,
              new Normal()
              ));

        locationList.put("woods", 
            new Location("Woods", 
              """
              The sea of trees is giving you the creeps. 
              The leafless branches 
              But in the distance you see a cabin.
              I wonder if anyone's inside?
              """
              ));

        // Add location as nodes in the graph
        locationList.forEach((k, v) -> locations.addNode(v.stringLocation().getValue()));
        // I need to refactor stringLocation to something better but... nah
        locationList.forEach((k, v) -> commandSystem.addNoun(v.stringLocation().getKey()));
        
        /* Yeah, I know, I could refactor this to be a tad bit tidier but 
         * in the spirit of Java's verbosity, here's a monstrosity.
         * Edge values represent how much toxicity is taxed when traversed
         */
        locations.putEdgeValue(locationList.get("start"), locationList.get("river"), 5);
        locations.putEdgeValue(locationList.get("start"), locationList.get("road"), 10);
        locations.putEdgeValue(locationList.get("start"), locationList.get("tree"), 6);
        locations.putEdgeValue(locationList.get("tree"), locationList.get("opening"), 2);
        locations.putEdgeValue(locationList.get("tree"), locationList.get("woods"), 5);
        locations.putEdgeValue(locationList.get("woods"), locationList.get("home"), 12);
        locations.putEdgeValue(locationList.get("road"), locationList.get("home"), 5);
        locations.putEdgeValue(locationList.get("road"), locationList.get("conbini"), 7);
        locations.putEdgeValue(locationList.get("opening"), locationList.get("campfire"), 9);

        // Spawn player at starting point
        player = new Player(locationList.get("start"));

        // Add player items as nouns
        player.itemList.keySet().forEach(commandSystem::addNoun);

        // Adds all String key value of locations as nouns
        locations.nodes().forEach(l -> commandSystem.addNoun(l.stringLocation().getKey()));

        // Add entities as nouns
        locations.nodes().forEach(l -> commandSystem.addNoun(l.getEntityInLocation().getName()));

        // Adds commands
        commandSystem.addVerb("look", "look, look <item>, look <entity>");
        commandSystem.addVerb("walk", "walk <location>");
        commandSystem.addVerb("hit", "hit, hit <item> <entity>");
        commandSystem.addVerb("throw", "throw <item>");
        commandSystem.addVerb("light", "light <item>, light <item> <entity>");
        commandSystem.addVerb("use", "use <item> <entity>");
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
