package Final_Project;

import java.util.ArrayList;

public class Player {
  private
    int toxicity = 100;
    ArrayList<String> availableActions;
    Location currentLocation;
    ArrayList<Item> itemList; 
    Lighter lighter;
    Stick stick;
    Wallet wallet;

public Player(Location location) {

    // Load player with default items
    lighter = new Lighter();
    stick = new Stick();
    wallet = new Wallet();
    itemList = new ArrayList<>();
    itemList.add(lighter);
    itemList.add(wallet);
    itemList.add(stick);

    }

    void setToxicity(int toxicity) {
      this.toxicity = toxicity;
    }

    void setCurrentLocation(Location currentLocation) {
      this.currentLocation = currentLocation;
    }

    int getToxicity() {
        return toxicity;
    }

    Location getCurrentLocation() {
      return currentLocation;
    }

    public void look() {
      currentLocation.getDesc();
    }

    public void look(Entity entity) {
      currentLocation.getEntityInLocation().getDesc();
    }

    public void walk(Location location) {
      System.out.println("You walked to " + location.getName());
      currentLocation = location;
    }

    public ArrayList<Item> getItemList() {
      return itemList;
    }

}
