package Final_Project;
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
      entityInLocation = new Entity();
      this.hasEntity = false;
    }

    public Location(String name, String descripton, Entity entityInLocation) {
      this.name = name;
      this.description = descripton;
      this.entityInLocation = entityInLocation;
      this.hasEntity = true;
    }

    public String getDesc() {
      return (hasEntity) ? 
        String.format(
        """
        There's a [%s][nl]
        %s[nl]
        """,
        entityInLocation.getName(),
        entityInLocation.getDesc()
          ) 
        : 
        description ;
    }

    public void setDesc(String description) {
      this.description = description;
    }

    public Entity getEntityInLocation() {
      return entityInLocation;
    }

    public void setHasEntity(boolean hasEntity) {
      this.hasEntity = hasEntity;
    }

    public void removeEntity() {
      hasEntity = false;
      entityInLocation = null;
    }

    public Map.Entry<String, Location> stringLocation() {
      return Map.entry(name.toLowerCase(), this);
    }
}
