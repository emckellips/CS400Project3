// --== CS400 File Header Information ==--
// Name: Aidan Lonergan
// Email: alonergan@wisc.edu
// Team: IG Red
// Role: Data Wrangler
// TA: Sid
// Lecturer: Gary Dahl
// Notes to Grader: n/a
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class LocationList implements LocationListInterface {

    // ArrayList of String arrays representing the node connections as follows
    // [source, target_0, weight_0, ..., target_n, weight_n]
    private ArrayList<String[]> nodes;

    /**
     * Reads the csv file and returns an ArrayList of nodes represented
     * as String arrays
     * @param fr        FileReader object
     * @return nodes    ArrayList of nodes
     */
    public ArrayList<String[]> readData(FileReader fr) {
        try {
            nodes = new ArrayList<String[]>();
            BufferedReader r = new BufferedReader(fr);
            String curr = r.readLine();

            while (curr != null) {
                nodes.add(curr.split(",", 100));
                curr = r.readLine();
            }
            
            return nodes;
        } catch (NoSuchElementException | IOException e) {
            System.out.println("No file found");
        }
        return null;
    }

    /**
     * Gets the source node with the name specified.
     * If no node is found it throws a NoSuchElementException
     * @param name      name of source node
     * @return          the nodes String[] representation
     */
    public String[] getLocation(String name) {
        for (String[] node : nodes) {
            if (node[0].equalsIgnoreCase(name)) {
                return node;
            }
        }
        throw new NoSuchElementException("No node with that name exists");
    }
    
    /**
     * Returns the number of source nodes on the graph
     */
    public int size() {
        return nodes.size();
    }

    // for testing and debugging
    public String[] get(int i) {
        return nodes.get(i);
    }

    
}
