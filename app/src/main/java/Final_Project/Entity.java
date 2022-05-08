package Final_Project;
import java.util.Map;

public class Entity {
    String name;
    String description;

    Entity() {
      name = "bob";
    }

    Entity(String name, String description) {
      this.name = name;
      this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return description;
    }

    public Map.Entry<String, Entity> keyVal() {
      return Map.entry(name.toLowerCase(), this);
    }
}

class Scary extends Entity {
  int aggression;
  
  Scary() {
    name = "kaonishi";
    description = "Scary";
    aggression = 0;
  }

  Scary(String name, String description, int aggression) {
    super(name, description);
    this.aggression = aggression;
  }

  public void setAggression(int aggression) {
    this.aggression = aggression;
  }

  public int getAggression() {
    return aggression;
  }

}

class Normal extends Entity {
  int fear; 

  Normal() {
    name = "dog";
    description = "Is that my dog? Did I forget to close the door? Should I go after him?";
    fear = 0;
  }

  Normal(String name, String description, int fear) {
    super(name, description);
    this.fear = fear;
  }
}

class Tripy extends Entity {
  int integrity;

  Tripy() {
    name = "peacock";
    description = "A Peacock sitting in a tree. It's majestic!";
    integrity = 5;
  }

  Tripy(String name, String description, int integrity) {
    super(name, description);
    this.integrity = integrity;
  }
}
