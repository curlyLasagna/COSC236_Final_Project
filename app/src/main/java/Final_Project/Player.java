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

    // Load player with default items
    itemList = new HashMap<>();
    itemActions = new HashMap<>();
    itemActionsEntity = new HashMap<>();
    itemActionsItem = new HashMap<>();
    itemList.put("lighter", new Lighter());
    itemList.put("wallet", new Wallet());
    itemList.put("stick", new Stick());

    // Add actions to interact with item
    itemActions.put("light", (i -> {
        System.out.println(i.getName() + " is being lit");
        ((Lighter)i).setGas(((Lighter)i).getGas() - 1);
        if (((Lighter)i).getGas() > 0) {
          System.out.println("Current gas: " + ((Lighter)i).getGas());
        } else {
          System.out.println("Out of gas");
        }
    }));

    itemActions.put("throw", (i -> {
      try {
        itemList.remove(i.getName());
        System.out.print("You threw " + i.getName());
        i = null;
      }
      catch (NullPointerException npe) {
        System.err.print("You threw it. Now you can't find it");
      }
    }));

    itemActions.put("hit", (i -> {
      System.out.print("You hit the air with the " + i.getName());
      toxicity -= 2;
    }));

    // Add actions that to interact with entities 
    itemActionsEntity.put("light", (i, e) -> {
      // If entity is Scary
      if(e instanceof Scary) {
        System.out.println(e.getName() + " backs away in surprise");
        ((Scary)e).setAggression(((Scary)e).getAggression() + 25);
      }

      // If entity is Normal
      else if(e instanceof Normal) {
        System.out.println(e.getName() + " runs away before you could approach it");

      }

      else if(e instanceof Tripy) {
        System.out.printf(
            """
            %s doesn't react\n", 
            It burns and shrinks to a distorted figure.
            It produces an awful toxic smell.
            It wasn't a peacock. It was a plastic bag
            """,
            e.getName());
        e = null;
      }
      else
        System.out.println("You want me to light what?");
      });

    // Add actions for items to interact with other items
    itemActionsItem.put("light", (x, y) -> {
      if(x instanceof Lighter && y instanceof Wallet) {
        System.out.println("You watch in delight as you burn your wallet");
      }

      else if(x instanceof Stick && y instanceof Lighter) {

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

    public void check() {
      System.out.println("The items in your inventory include:");
      itemList.keySet().forEach(i -> System.out.println("- " + i));
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
