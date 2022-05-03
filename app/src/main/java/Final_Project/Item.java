package Final_Project;
import java.util.ArrayList;
import java.util.Map;

/* This class represents an item that can be interacted with by the player. */
public class Item {
    private
      String name;
      String description;

    Item () {};

    Item(String name, String description) {
      this.name = name;
      this.description = description;
    }

    String getName() {
      return name;
    }

    String getDescription() {
      return description;
    }

    public Map.Entry<String, Item> keyVal() {
      return Map.entry(name.toLowerCase(), this);
    }
}

// Subclasses

class Lighter extends Item {
  private int gas;

  Lighter () {
    super("Lighter", "You have a white lighter.");
    this.gas = 3;
  }

  Lighter(String name, String description, int gas) {
    super();
    this.gas = gas;
  }
}

class Wallet extends Item {
  private ArrayList<Card> cards;
  Wallet() {
   super("Wallet", "You reach into your pocket pull out your wallet.");
   cards = new ArrayList<>();
   cards.add(new Card("Credit Card", "Capital Two credit card", false)); 
   cards.add(new Card("Home Swipe", "Swipe card to get in my house", true));
  }

  Wallet (String name, String description, ArrayList<Card> cards) {
    super(name, description);
    this.cards = cards;
  }
}

class Stick extends Item {
  int durability = 3;

  Stick() {
    super("Stick", "You picked up a stick that found on the ground.");
  }
  
  Stick(String name, String description, int durability) {
    super(name, description);
    this.durability = durability;
  }
}

class Card extends Item {
  private boolean accessHome = true;

  Card(String name, String description, boolean accessHome) {
    super(name, description);
    this.accessHome = accessHome;
  }

}
