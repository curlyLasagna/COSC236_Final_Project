package Final_Project;

public class Location {
  private
    String 
    name,
    description;
    Entity entityInLocation;

    public Location() {

    };

    public Location(String name, String description, Entity entity){
      entityInLocation = entity;
    };

    public String getName() {
        return name;
    }

    public String getDesc() {
        return description;
    }

    public Entity getEntityInLocation() {
      return entityInLocation;
    }

}



