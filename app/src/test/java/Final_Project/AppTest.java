/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package Final_Project;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.google.common.graph.*;

class AppTest {
    @Test void initClasses() {
        String errorMsg = "Class does not exist";
        Entity scaryTest = new Scary("Kaonashi", "Fucked", 3);
        Entity normaltest = new Normal("Dog", "Your cute dog", 4);
        Location locationTest = new Location("River", "It smells", scaryTest);
        Item itemTest = new Item();
        
        assertNotNull(locationTest, errorMsg);
        assertNotNull(itemTest, errorMsg);
        assertNotNull(scaryTest, errorMsg);
        assertEquals("Fucked", scaryTest.getDesc());
    }

}

class LocationTest {
    @Test
    void testLocation() {
      MutableValueGraph<String, Integer> locations 
        = ValueGraphBuilder.undirected().allowsSelfLoops(true).build();

      locations.addNode("Node 0");
      locations.addNode("Node 1");
      locations.addNode("Node 2");
    }
}
