/*
CommandSystem.java
For use in the Final project for COSC 236.
Based on starter code first developed by Prof. Dastyni Loksa

This class is the primary logic class for the system. It defines what commands are valid, 
and what happens when those commands are executed.  
*/

package Final_Project;
import java.util.*;

public class CommandSystem {
    private static int DISPLAY_WIDTH = 80;
    private GameState state;

    private List<String> nouns = new ArrayList<String>();
    private List<String> verbs = new ArrayList<String>();
    private List<String> verbDescription = new ArrayList<String>();

    /*
     * Constructor should defines all verbs that can be used in the commands and all
     * nouns the user can interact with.
     * 
     * Suggestion: These could all be loaded from a file.
     * 
     * Verb descriptions are stored in a parallel Arraylist with the Verbs and are
     * used when printing out the help menu (using the ? command).
     */
    public CommandSystem(GameState state) {
        this.state = state;
        DISPLAY_WIDTH = GameState.DISPLAY_WIDTH;
        // Assign verbs and descriptions here
        addVerb("?", "Help");
        addVerb("look", "Look at your current surroundings");
        addVerb("walk", "Walk to [Location]");
        addVerb("check", "See what items you have");
    }

    // When a command is only one Verb this method controls the result.
    public void executeVerb(String verb) {
      Player player = state.getPlayer();
        switch (verb) {
          case "look": {

            System.out.printf(
              """
              You're currently at %s 
              %s
              You can walk towards:
              """, 

              state.getPlayer()
                .getCurrentLocation()
                .stringLocation()
                .getKey(),

              state.getPlayer()
                .getCurrentLocation()
                .getDesc());

            // Print neighboring locations
            state.getLocations()
              .adjacentNodes(player.getCurrentLocation())
              .forEach(l -> System.out.println(l.stringLocation().getKey()));
        break;
        }

        case "walk": {
          // If not in the neighboring location, just print the description of the current location
          System.out.println("Walk where?");
          break;
        }

        case "hit": {
          player.hitAir();
          break;
        }

        case "check": {
          System.out.println("The items in your inventory include:");
          player.itemList.keySet().forEach(i -> System.out.println("- " + i));
          break;
        }
        case "?":
          printHelp();
        break;
        default: {
          // player.itemActions.get(verb);
        }
      break;
    }
  }

    // When a command is a Verb followed by a noun, this method controls the result.
    public <T> void executeVerbNoun(String verb, String noun) {
        Player player = state.getPlayer();
        String resultString = "";

        resultString = 
          switch (verb) {
            case "walk", "w" -> {
              // Refactor needed
              // Store string(location name) key and location value 
              ArrayList<Map.Entry <String, Location> > s = new ArrayList<>();
              state.getLocations().adjacentNodes(
                player
                .getCurrentLocation())
                .forEach(x -> s.add(x.stringLocation()));

              /* If player travels, reduce player toxicity 
               * Sets the player's current location to input noun */
              
              for(Map.Entry<String, Location> x : s) 
                if(noun.equals(x.getKey())) {
                  int tax = 
                    state.getPlayer().getToxicity() - state.getLocations()
                    .edgeValue(
                        player.getCurrentLocation(), 
                        x.getValue())
                    .get();

                  player.setToxicity(tax);
                  player.walk(x.getValue());
                }

              yield String.format(
                  "You walked to %s%n%s%n", 
                  state.getPlayer().currentLocation.stringLocation().getKey(),
                  state.getPlayer().currentLocation.getDesc()
                  );
            }

            case "look", "l" -> {
              String itemDescription = "";
              try {
                itemDescription = player.itemList.get(noun).getDescription();
              } catch (NullPointerException e) {
                System.err.print("Look at what?");
                }
                yield itemDescription;
              }

            default -> {
              player.itemActions.get(verb).accept(player.itemList.get(noun));
              yield "";
            } 
        };
      System.out.println(formatStringToScreenWidth(resultString));
    }

    // When a command is a Verb followed by two nouns, this method controls the result.
    public void executeVerbNounNoun(String verb, String item, String supercalifragilisticexpialidocious) {
      // Nullify the entities if they're dead and reomve them from description
      // Need to find a way to differentiate the ojb being acted upon e.g. Item & Entity
      Player player = state.getPlayer();
        player.itemActionsEntity.get(verb).accept(
          player.itemList.get(item), 
          player.currentLocation.getEntityInLocation()
      );
    }

    /*
     * Prints out the help menu. Goes through all verbs and verbDescriptions
     * printing a list of all commands the user can use.
     */
    public void printHelp() {
        String s1 = "";
        while (s1.length() < DISPLAY_WIDTH)
            s1 += "-";

        String s2 = "";
        while (s2.length() < DISPLAY_WIDTH) {
            if (s2.length() == (DISPLAY_WIDTH / 2 - 10)) 
                s2 += " Commands ";
             else 
                s2 += " ";
        }

        System.out.println("\n\n" + s1 + "\n" + s2 + "\n" + s1 + "\n");
                for (String v : verbs) 
                    System.out.printf("%-8s  %s", v, formatMenuString(verbDescription.get(verbs.indexOf(v))));
    }

    // Allows the client code to check to see if a verb is in the game.
    public boolean hasVerb(String string) {
        return verbs.contains(string);
    }

    // Allows the client code to check to see if a noun is in the game.
    public boolean hasNoun(String string) {
        return nouns.contains(string);
    }

    //Used to format the help menu
    public String formatMenuString(String longString) {
        String result = "";
        Scanner chop = new Scanner(longString);
        int charLength = 0;

        while (chop.hasNext()) {
            String next = chop.next();
            charLength += next.length();
            result += next + " ";
            if (charLength >= (DISPLAY_WIDTH - 30)) {
                result += "\n          ";
                charLength = 0;
            }
        }
        chop.close();
        return result + "\n\n";
    }

    // Adds a noun to the noun list
    // lets the command system know this is something you an interact with.
    public void addNoun(String string) {
        if (!nouns.contains(string.toLowerCase()))
            nouns.add(string.toLowerCase());
    }

    // Adds a verb to the verb list and the description to the parallel description list
    // Adding a verb lets the command system know you want this to be a command.
    public void addVerb(String verb, String description) {
        verbs.add(verb.toLowerCase());
        verbDescription.add(description.toLowerCase());
    }

    // formats a string to DISPLAY_WIDTH character width.
    // Used when getting descriptions from items/locations and printing them to the screen.
    // use [nl] for a newline in a string in a description etc.
    public String formatStringToScreenWidth(String longString) {

        Scanner chop = new Scanner(longString);
        String result = "";
        int charLength = 0;
        boolean addSpace = true;

        while (chop.hasNext()) {

            // Get our next word in the string.
            String next = chop.next();

            // Add the legnth to our charLength.
            charLength += next.length() + 1;

            // Find and replace any special newline characters [nl] with \n.
            if (next.contains("[nl]")) {
                // Find the index after our [nl] characters.
                int secondHalf = next.indexOf("[nl]") + 4;

                // Set charLength to the number of characters after the [nl],
                // because that will be the beginnig of a new line.
                if (secondHalf < next.length()) {
                    charLength = secondHalf;
                } else {
                    charLength = 0;
                    addSpace = false; // Do not add space after if this ended with a newline character.
                }

                // Now actually replace the [nl] with the newline character
                next = next.replace("[nl]", "\n");

            }

            // Add the word to the result.
            result += next;

            // Only add a space if our special case did not happen.
            if (addSpace)
                result += " ";

            // Normally we add a space after a word, prepare for that.
            addSpace = true;

            if (charLength >= DISPLAY_WIDTH) {
                result += "\n";
                charLength = 0;
            }
        }
        chop.close();
        return result;
    }
}
