package Final_Project;

public class Entity {
    String name;
    String description;

    public String getName() {
        return name;
    }

    public String getDesc() {
        return description;
    }
}

class Scary extends Entity {
  int aggression = 0;
  
  Scary() {

  }

}

class Normal extends Entity {
  int fear = 0;

  Normal() {

  }

}

class Tripyy extends Entity {
  int integrity = 100;

  Tripyy() {

  }

}
