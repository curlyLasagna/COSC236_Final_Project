package Final_Project;

import java.util.HashMap;
import java.util.function.*;

public class Player {
  private int toxicity;
  Location currentLocation;
  HashMap<String, Item> itemList; 
  HashMap<String, Consumer<? super Item> > itemActions;
  HashMap<String, BiConsumer<? super Item, ? super Entity> > itemActionsEntity;

public Player(Location location) {

    toxicity = 50;
    // Load player with default items
    itemList = new HashMap<>();
    itemActions = new HashMap<>();
    itemActionsEntity = new HashMap<>();
    itemList.put("lighter", new Lighter());
    itemList.put("wallet", new Wallet());
    itemList.put("stick", new Stick());
    currentLocation = location;
    setItemActions();
    setItemEntityActions();
    }

    /* Adds a key value pair for itemActions Hashmap
     * This was to split the code so I don't get lost 
     * I can smell this code. It reeks
     */
    private void setItemActions() {
      itemActions.put("light", (i -> {
        System.out.println(i.getName() + " is being lit");
        ((Lighter)i).useGas(1);
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
    }

    /* Adds a key value pair for itemActionsEntity Hashmap
     * This was to split the code so I don't get lost 
     * I can do better than this
     */

    private void setItemEntityActions() {
    // Downcast hell
    itemActionsEntity.put("light", (i, e) -> {
      if(!(i instanceof Lighter))
        System.out.print("You can't do that with " + i.getName());

      // Scary entity
      if(e instanceof Scary) {
        System.out.printf(
            """
            %s is trying to look for something... 
            "Can you help me find my cigarettes?
            asks %s
            You just stand in fear as he continues to
            look for his cigarettes
            """,
            e.getName(),
            e.getName()
            );
        ((Lighter)i).useGas(1);
        toxicity -= 3;
        // Increase scary entity aggression
        ((Scary)e).setAggression(((Scary)e).getAggression() + 1);

        // He's had it
        if(((Scary)e).getAggression() > 5) {
          System.out.printf(
              """
              "You're a different type of nutjob.
              This is getting weird. I'm outta here!"
              %s picks himself up and leaves
              He wasn't as scary as I thought he was...
              Wait, it was just a homeless guy
              """,
              e.getName()
              );
          currentLocation.removeEntity();
        }
      }

      // Normal entity
      else if(e instanceof Normal) {
        System.out.println(e.getName() + " runs away before you could approach it");
        currentLocation.removeEntity();
      }

      // Tripy entity
      else if(e instanceof Tripy) {
        System.out.printf(
            """
            %s doesn't react, 
            It burns and shrinks to a distorted figure.
            It produces an awful, toxic smell.
            It wasn't a peacock. It was a plastic bag
            """,
            e.getName());
        currentLocation.removeEntity();
      }
      else
        System.out.println("You want me to light what?");

      });

    itemActionsEntity.put("hit", (i, e) -> {
      if(!(i instanceof Stick))
        System.out.print("You can't do that with " + i.getName());

      else {
        // Scary entity
        if(e instanceof Scary) {
          System.out.println(e.getName() + " tells you to knock it off");
          ((Scary)e).setAggression(((Scary)e).getAggression() + 1);
          ((Stick)i).useStick(1);
          if(((Scary)e).getAggression() > 2) {
            System.out.printf(
                """
                "That hurts man, what the hell!
                Have some compassion, I'm not some animal".
                %s leaves and you stand in silence in shame.
                It was just a homeless guy resting
                """, e.getName());
            currentLocation.removeEntity();
          }
        }

        // Normal entity
        else if(e instanceof Normal) {
          System.out.println(e.getName() + " runs away before you could approach it");
          currentLocation.removeEntity();
        } 

        // Tripy entity
        else if(e instanceof Tripy) {
          System.out.printf(
              """
              %s doesn't react, 
              Its body conforms to the stick and wraps around it
              You pull the stick back, in a fighting stance
              It looks at you confusingly
              """,
              e.getName());
          ((Tripy)e).integrity -= 1;

          if(((Tripy)e).integrity < 0) {
            currentLocation.removeEntity();
            System.out.println(
                """
                You've knocked the peacock off the tree
                It flies away into the direction of the wind
                It's so majestic it brought a tear to your eye
                But after looking at it a little more...
                It said Y'allMart 
                """
                );
            }
          }
        else
          System.out.println("I don't know what you want me to hit but you have issues");
        }
    });
  }

    void setToxicity(int toxicity) {
      this.toxicity = toxicity;
      if (getToxicity() < 0) {
        this.toxicity = 0;
      System.out.println("Current toxicity: " + toxicity);
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

    public void reduceToxicity(int toxicity) {
      this.toxicity -= toxicity;
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
      reduceToxicity(2);
    }

    public void walk(Location location) {
      currentLocation = location;
    }
}
