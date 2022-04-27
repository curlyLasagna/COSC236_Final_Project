package Final_Project;

public class Player {

    public Player() {
    int toxicity = 100;
    Location currentLocation;
    Item lighter;
    Item stick;
    Item wallet;
    Entity scary;
    Entity trippy;
    Entity normal;

    lighter = new Lighter( "Lighter", "You have a white lighter", 0);
    stick = new Stick("Stick", "You have a stick that you picked up from the ground", 3);

    // need to create card array list
    wallet = new Wallet("Wallet", "You reach into your pocket and pull out your wallet", );

    // scary = new Entity();
    // scary.name = "scary";
    // scary.description = "You see a tall shadow with what seems to be a white mask standing silently near the stream bank. The creature does not move as you step closer";

    }

    public int getToxicity() {
        return toxicity;
    }

    public void look() {
        // state.currentLocation.getDesc();
    }

}
