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
    name = "kaonashi";
    description = 
      """
      I've seen this monster before in the movies.[nl]
      It dons a mask that resembles a face
      It seems to be asleep. I wouldn't bother it
      """;
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
    description = 
      """
      I think that's my dog. Why is it eating grass though?
      I guess my dog got loose. 
      Did I forget to close the door? 
      Should I go after him?
      """;
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
    description =
    """
    A peacock sitting in a tree. It's majestic!
    It sways aggressively with the motion of the wind
    It sure does have a peculiar shape
    I could stare at it for hours on end
    """;
    integrity = 5;
  }

  Tripy(String name, String description, int integrity) {
    super(name, description);
    this.integrity = integrity;
  }
}
