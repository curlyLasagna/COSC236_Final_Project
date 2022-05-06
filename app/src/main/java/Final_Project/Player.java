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
      itemList.remove(s);
      System.out.println("You threw " + s.getName());
      s = null;
    }));

    // Could be a problem in the future
    itemActions.put("hit", (s -> {
      System.out.println("You hit the air with the " + s.getName());
      this.toxicity = toxicity - 2;
    }));

    currentLocation = location;
    }
    
   

    void setToxicity(int toxicity) {
      this.toxicity = toxicity;
      if (getToxicity() < 0) {
        this.toxicity = 0;
        System.out.println("Current toxcicity: " + getToxicity());
        System.out.println("Sober");
      } else {
        System.out.println("Current toxcicity: " + getToxicity());
        System.out.println("Not Sober");
      }
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

    public void hitAir() {
      System.out.println("You hit at the air with your hands");
      this.toxicity = toxicity - 2;
    }

    public void walk(Location location) {
      currentLocation = location;
    }
}
