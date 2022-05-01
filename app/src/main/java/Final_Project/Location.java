package Final_Project;

import java.util.Map.Entry;
import java.util.Map;

public class Location {
  private
    String 
    name,
    description;
  private boolean hasEntity;
  private Entity entityInLocation;

    public Location() {};

    public Location(String name, String description) {
      this.name = name;
      this.description = description;
      this.hasEntity = false;
    }

    public Location(String name, String descripton, Entity entityInLocation) {
      this.name = name;
      this.description = descripton;
      this.entityInLocation = entityInLocation;
      this.hasEntity = true;
    }

    public String getDesc() {
        return description;
    }

    public void setDesc(String description) {
      this.description = description;
    }

    public Entity getEntityInLocation() {
      return entityInLocation;
    }

    public Map.Entry<String, Location> stringLocation() {
      return Map.entry(name.toLowerCase(), this);
    }

}



