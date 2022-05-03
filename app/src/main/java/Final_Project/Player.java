package Final_Project;

import java.util.ArrayList;

public class Player {
  private
    int toxicity = 100;
    Location currentLocation;
    ArrayList<String> itemActions;
    ArrayList<Item> itemList; 

public Player(Location location) {

    // Load player with default items
    itemList = new ArrayList<>();
    itemActions = new ArrayList<>();
    itemList.add(new Lighter());
    itemList.add(new Wallet());
    itemList.add(new Stick());
    itemActions.add("light");
    itemActions.add("throw");
    itemActions.add("hit");
    currentLocation = location;
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
      currentLocation = location;
    }

    public ArrayList<Item> getItemList() {
      return itemList;
    }

}
