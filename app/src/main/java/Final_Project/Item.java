package Final_Project;
import java.util.ArrayList;

/*
Item.java
For use in the Final project for COSC 236.
Based on starter code first developed by Prof. Dastyni Loksa

This class represents an item that can be interacted with by the player.  
*/
public class Item {
    private String 
      name,
      shortDesc,
      description;

    Item () {};
    
    Item(String name, String description) {
      this.name = name;
      this.description = description;
    }

}

// Subclasses

class Lighter extends Item {
  private int gas;

  Lighter () {
    this.gas = 3;
  }

  Lighter(String name, String description, int gas) {
    super();
    this.gas = gas;
  }
}

class Wallet extends Item {
  private ArrayList<Card> cards = new ArrayList<Card>();
  Wallet (String name, String description, ArrayList<Card> cards) {
    super();
    this.cards = cards;
  }
}

class Stick extends Item {
  private String name = "Stick";
  int durability = 3;

}

class Card extends Item {
  private String name = "Card";
  private boolean accessHome = true;


}
