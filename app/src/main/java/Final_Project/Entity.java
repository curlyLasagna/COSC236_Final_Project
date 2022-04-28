package Final_Project;

public class Entity {
    String name;
    String description;

    Entity() {}

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
}

class Scary extends Entity {
  int aggression;
  
  Scary() {
    name = "Kaonishi";
    description = "Scary";
    aggression = 0;
  }

  Scary(String name, String description, int aggression) {
    super(name, description);
    this.aggression = aggression;
  }

}

class Normal extends Entity {
  int fear; 

  Normal() {
    name = "Dog";
    description = "Is that my dog? Did I forget to close the door? Should I get him?";
    fear = 0;
  }

  Normal(String name, String description, int fear) {
    super(name, description);
    this.fear = fear;
  }

}

class Tripy extends Entity {
  int integrity = 100;

  Tripy() {
    name = "Peacock";
    description = "A Peacock sitting in a tree. It's majestic!";
    integrity = 100;
  }

  Tripy(String name, String description, int integrity) {
    super(name, description);
    this.integrity = integrity;
  }
}
