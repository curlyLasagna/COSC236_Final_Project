package Final_Project;

import java.util.HashMap;
import java.util.function.Consumer;

import com.google.common.collect.Table;

public class Player {
  private int toxicity = 20;
  Location currentLocation;
  HashMap<String, Item> itemList; 
  HashMap<String, Consumer<? super Item> > itemActions;
  HashMap<String, Consumer<? super Item> > itemActionsEntity;
  Table<String, Item, Entity> x;

public Player(Location location) {

    // Load player with default items
    itemList = new HashMap<>();
    itemActions = new HashMap<>();
    itemList.put("lighter", new Lighter());
    itemList.put("wallet", new Wallet());
    itemList.put("stick", new Stick());

    // itemAction verbs will run the anonymous functions
    itemActions.put("light", (s -> {
      System.out.println(s.getName() + " is being lit");
      ((Lighter)s).setGas(((Lighter)s).getGas() - 1);
      System.out.println("Current gas: " + ((Lighter)s).getGas());
    }));

    //
    itemActions.put("throw", (s -> {
    }));

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
}
