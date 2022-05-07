package Final_Project;

import java.util.HashMap;
import java.util.function.*;

public class Player {
  private int toxicity = 20;
  Location currentLocation;
  HashMap<String, Item> itemList; 
  HashMap<String, Consumer<? super Item> > itemActions;
  HashMap<String, BiConsumer<? super Item, ? super Item> > itemActionsItem;
  HashMap<String, BiConsumer<? super Item, ? super Entity> > itemActionsEntity;

public Player(Location location) {

    // This is so boring 🥱
    // Load player with default items
    itemList = new HashMap<>();
    itemActions = new HashMap<>();
    itemActionsEntity = new HashMap<>();
    itemList.put("lighter", new Lighter());
    itemList.put("wallet", new Wallet());
    itemList.put("stick", new Stick());

    // itemAction verbs will run the anonymous functions
    itemActions.put("light", (i -> {
      System.out.println(i.getName() + " is being lit");
      ((Lighter)i).setGas(((Lighter)i).getGas() - 1);
      System.out.println("Current gas: " + ((Lighter)i).getGas());
    }));

    itemActions.put("throw", (i -> {
      itemList.remove(i.getName());
      System.out.println("You threw " + i.getName());
      i = null;
    }));

    itemActions.put("hit", (i -> {
      System.out.println("You hit the air with the " + i.getName());
      this.toxicity = toxicity - 2;
    }));

    // Bruh... I'm about to sleep writing all these
    itemActionsEntity.put("light", (i, e) -> {
      if(e instanceof Scary) {
        System.out.println(e.getName() + " backs away in surprise");
        ((Scary)e).setAggression(((Scary)e).getAggression() + 25);
      }

      else if(e instanceof Normal) {
        System.out.println(e.getName() + " runs away before you could approach it");
      }

      else if(e instanceof Tripy) {
        System.out.println(e.getName() + " doesn't react. It simply burns");
      }
      else
        System.out.println("You want me to light what?");
      });

    itemActionsItem.put("light", (x, y) -> {
      if(y instanceof Wallet) {
        System.out.println("You watch in delight as you burn your wallet");
      }
    });

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
