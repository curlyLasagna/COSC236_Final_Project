/* UML document for starter code:
    https://lucid.app/lucidchart/285ca85b-58f3-40e4-a98a-4d54bd2fc03d/view?page=0_0#


App.java
For use in the Final project for COSC 236.
Based on starter code first developed by Prof. Dastyni Loksa

This is the driver class of the project. It is in charge of the following:

- Setting up the game by creating the GameState.
- The main game loop deciding when the game quits.
- The input from the user and routing to the correct methods in the CommandSystem.

*/
package Final_Project;
import java.util.*;

public class App {
    static Scanner in = new Scanner(System.in);

    public static void main(String[] args) throws Exception {

        // Create needed objects for the game.
        GameState state = new GameState();
        // Store the command system for easy reference in the client code.
        CommandSystem commandSystem = state.getCommandSystem();

        // This controls if the game should continue running.
        boolean gameRunning = true;

        // Starting info
        System.out.println(
            """
            You started the night off with your friends around a campfire.
            It had already started getting dark by the time you arrived.
            After taking some questionable substances with your friends,
            you have the great idea to wander off by yourself into the 
            surrounding woods. You can't think clearly and soon you lose
            sight of the campfire and the group.

            You're now left alone in the middle of the woods. You want to find your 
            friends again before the effects of those substances wear off. Otherwise,
            this whole situation will be a lot worse if you have to deal with it
            while being completley sober.
            
            Honestly, anything is better than being out here alone.

            Try to make it back to the campfire, or at least somewhere,
            before your high levels off. You're here to party. 
            Certain movements and interactions
            will lower your toxicity so be wise.
            """);


        // The main game loop.
        while (gameRunning) {

            //ENDINGS:
            // good ending with the campfire
            if (state.getPlayer().getCurrentLocation().equals(state.locationList.get("campfire"))) { //checks if player location is campfire
                System.out.println(
                    """
                    Suddenly you see the glow of the campfire from earlier.
                    You hear your friends as you approach the site.
                    You've managed to find your way through the forest and can
                    now continue to have a good night.

                    GAME OVER
                    """);
                System.exit(0);
            }

            // ending for toxicity being 0 and sober
            if (state.getPlayer().getToxicity() == 0) { // if player is sober, toxicity == 0
                System.out.println(
                    """
                    You've walked around so much and it's been so long since
                    you lost your friends. The effects of those
                    substances from earlier have worn off, and now you're
                    cold, alone, tired, and sober. 
                    "That was a massive waste" you tell yourself.
                    Your head is pounding.
                    You just want to sleep. 
                    You lie down and close your eyes.
                    The sun is about to come up.

                    GAME OVER
                    """);
                System.exit(0);
            }

            // bad ending with 7-11 store
            if (state.getPlayer().getCurrentLocation().equals(state.locationList.get("conbini"))) { // if player location is store
                enterPrompt(
                    """
                    Oh no. Coming to 7-11 was the worst decision you could have made.
                    The are cops in here, and they immediatley recognize you.
                    Turns out you're a wanted crimminal for a multitide of reasons and
                    they arrest you. What a horrible way to end the night.
        `
                    GAME OVER
                    """

                );
            }

            // neutral ending at players home
            if (state.getPlayer().getCurrentLocation().equals(state.locationList.get("home"))) { // if player location is home
                enterPrompt(
                    """
                    You walk into your house exhausted.
                    You can't be bothered to try and find your friends 
                    after going through all of that
                    so you decide to call it a night and pass out on the couch.

                    GAME OVER
                    """
                );
            }

            // Gets input from the user in an array of strings that they typed in.
            String[] input = getCommand();
            if (input.length < 1) {
                System.out.println("Unknown command. Type ? for help.");

            } else if (input[0].equals("quit")) {
                gameRunning = false;
                System.out.println("Goodbye.");
                in.close();

                // No arg commands
            } else if (input.length == 1 && commandSystem.hasVerb(input[0])) {
                commandSystem.executeVerb(input[0]);

                // Command has 2 words - should be verb and noun.
            } else if (input.length == 2) {
                // Validate that the commands are known verb/nouns
                if (!commandSystem.hasVerb(input[0])) {
                    unknownCommand(input[0]);
                } else if (!commandSystem.hasNoun(input[1])) {
                    unknownCommand(input[1]);
                } else {
                    // Run command
                    commandSystem.executeVerbNoun(input[0], input[1]);
                }

                // command has 3 words - should be verb noun noun
            } else if (input.length == 3) {
                // Validate that the commands are known verb/nouns
                if (!commandSystem.hasVerb(input[0])) {
                    unknownCommand(input[0]);
                } else if (!commandSystem.hasNoun(input[1])) {
                    unknownCommand(input[1]);
                } else if (!commandSystem.hasNoun(input[2])) {
                    unknownCommand(input[2]);
                } else {
                    // Run command
                    commandSystem.executeVerbNounNoun(input[0], input[1], input[2]);
                }

            // Deal with any possible unknown structure/command
            } else {
                if(input.length > 1){
                    String userInput = "";
                    
                    for(String s :input)
                        userInput += s+" ";

                    unknownCommand(userInput);

                } else {
                    unknownCommand(input[0]);
                }
            }
        }

    }

    // Gets input from the user
    // seperates the input into each word (determined by whitespace)
    // returns an array with each word an element of the array.
    public static String[] getCommand() {

        in = new Scanner(System.in);
        System.out.println("\n------------------------------");
        System.out.print("What would you like to do? >  ");
        String input = in.nextLine();
        System.out.println();
        return input.toLowerCase().split("\\s+");
    }

    // Used to let the user know that what they typed as a command is not understood.
    public static void unknownCommand(String input) {
      System.out.println("I don't understand '" + input + "'. Type ? for help.");
    }

    public static void enterPrompt(String ending) {
        System.out.print("Do you want to enter? (yes or no) > ");
        String choice = "";
        while(!choice.equals("yes") || !choice.equals("no")) {
            choice = in.nextLine();
            choice = choice.toLowerCase();
            if (choice.equals("yes")) {
                System.out.println(ending);
                System.exit(0);
            } else if (choice.equals("no")) {
                System.out.println("You decide to wander around more.");
                break;
            } else {
                System.out.println("Invalid choice. Yes or no?");
                System.out.print("Do you want to enter? > ");
            }
        }
    }

}
