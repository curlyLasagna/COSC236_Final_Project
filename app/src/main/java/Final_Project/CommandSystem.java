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

    private List<String> verbs = new ArrayList<String>();
    private List<String> verbDescription = new ArrayList<String>();
    private List<String> nouns = new ArrayList<String>();

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
        addVerb("?", "Show this help screen.");
        addVerb("look",
                "Use the look command by itself to look in your current area. \nYou can also look at a person or object by ntyping look and the name of what you want to look at.\nExample: look book");
        addVerb("l", "Same as the look command.");
        addVerb("quit", "Quit the game."); // NOTE: In the starter code, this is handeled by the client code - not the
                                           // CommandSystem.
    }

    // When a command is only one Verb this method controls the result.
    public void executeVerb(String verb) {
        switch (verb) {
        case "l":
        case "look": // will show the description of the current room (stored in the state object)
            System.out.println("You look around.");
            break;
        case "?":
            this.printHelp();
            break;
        }
    }

    // When a command is a Verb followed by a noun, this method controls the result.
    public void executeVerbNoun(String verb, String noun) {
        // Initilize the string that we will use as a response to player input.
        String resultString = "";

        switch (verb) { // Deciddes what to do based on each verb
        case "l":
        case "look":
            switch (noun) { // for the given verb, decide what to do based on what noun was entered
            case "mat":
                /*
                 * This is extremely simple and hard coded. You should figure out a way to get
                 * the description from the mat itself and print that out here.
                 */
                resultString += "You look at the welcome mat. You see nothing special.";
                break;

            // You cound design a way to look at any item without having to specify how to
            // deal with each of them.
            // That way you can code special cases for some items, and others would just use
            // default behavior.
            // This is HIGHLY encouraged. (It will save time and headaches!)
            default:
            }
        }

        System.out.println(formatStringToScreenWidth(resultString));
    }

    // When a command is a Verb followed by two nouns, this method controls the
    // result.
    public void executeVerbNounNoun(String string, String string2, String string3) {
    // 3 nested switch statements

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
            if (s2.length() == (DISPLAY_WIDTH / 2 - 10)) {
                s2 += " Commands ";
            } else {
                s2 += " ";
            }
        }

        System.out.println("\n\n" + s1 + "\n" + s2 + "\n" + s1 + "\n");
        for (String v : verbs) {
            // System.out.printp(v + " --> " + verbDescription.get(verbs.indexOf(v)));
            System.out.printf("%-8s  %s", v, formatMenuString(verbDescription.get(verbs.indexOf(v))));
        }
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

}
