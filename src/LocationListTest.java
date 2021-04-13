import static org.junit.jupiter.api.Assertions.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.zip.DataFormatException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LocationListTest {
    @BeforeEach
    void beforeEach() {
        LocationList nodes = new LocationList();
        try {
            FileReader fr = new FileReader("src/NodeDataP3.csv");
            nodes.readData(fr);
        } catch(FileNotFoundException e) {
            System.out.println("Failed to find file with name specified");
        } catch(DataFormatException e) {
            System.out.println("Encountered an DataFormatException when parsing file");
        } catch(IOException e) {
            System.out.println("Encountered an IOException when parsing file")
        }

    }

    @Test 
    void testGetLocation() {
        try {
            String[] node = nodes.getLocation("Chicago");
            assertEquals(node[0], "Chicago");
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
    }

    @Test 
    void testSize() {
        int size = nodes.size();
        if (size != 10) {
            fail("The size returned was not correct");
        }
    }
}