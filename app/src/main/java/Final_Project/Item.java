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
    super("lighter", "You have a white lighter.");
    this.gas = 3;
  }

  Lighter(String name, String description, int gas) {
    super();
    this.gas = gas;
  }

  void setGas(int gas) {
    this.gas = gas;
  }

  int getGas() { return gas; }

  void useGas(int gas) {
    if (this.gas > 0) 
      this.gas -= gas;
    else 
      System.out.println(super.getName() + " is out of gas");
  }
}

class Wallet extends Item {
  private ArrayList<Card> cards;
  Wallet() {
   super("wallet", 
       """
       It's my fancy leather wallet.
       "It’s good to have money and the things that money can buy, 
       but it’s good, too, to check up once in a while and 
       make sure that you haven’t lost the things that money can’t buy"
       Why do I know that quote by heart?
       Also, where did my licence go?
       """
       );
   cards = new ArrayList<>();
   cards.add(new Card("Credit Card", "Capital ∫5x(x√−x2)dx credit card", false)); 
   cards.add(new Card("Home Swipe", "Swipe card to get in my house", true));
  }

  Wallet (String name, String description, ArrayList<Card> cards) {
    super(name, description);
    this.cards = cards;
  }

  @Override
  public String getDescription() {
    System.out.println(super.getDescription());
    String cardList = "Cards in wallet: ";
    // Eh em, Katie, you were supposed to list each cards description
    for (int i = 0; i < cards.size(); i++ ) {
      cardList += cards.get(i).getName() + ": " + cards.get(i).getDescription();
    }
    return cardList;
  }
}

class Stick extends Item {
  int durability = 3;

  Stick() {
    super("stick", "It's a stick you found on the ground for self defense.\nIt looks flimsy");
  }
  
  Stick(String name, String description, int durability) {
    super(name, description);
    this.durability = durability;
  }

  void useStick(int hit) {
    durability -= hit;
  }
}

class Card extends Item {
  // Sorry oh poor unused variable
  // You'll find a loving home some time whenever
  private boolean accessHome = true;

  Card(String name, String description, boolean accessHome) {
    super(name, description);
    this.accessHome = accessHome;
  }
}
