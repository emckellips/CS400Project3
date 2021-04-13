// --== CS400 File Header Information ==--
// Name: Ryan Toh
// Email: retoh@wisc.edu
// Team: IG Red
// Role: Frontend Developer
// TA: Sid
// Lecturer: Gary Dahl
// Notes to Grader: This is a dummy class as our backend did not complete his part on time.

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class DummyBackend {
    private LocationList locationList;
    private ArrayList<String[]> data;
    
    public DummyBackend(FileReader fr) {
        locationList = new LocationList();
        data = locationList.readData(fr);
    }

    float getTotalCosts(String start, String end) {
        return 123.4f;
    }

    List<String> getPath(String start, String end) {
        List<String> path = new ArrayList<>();
        path.add("A");
        path.add("B");
        path.add("C");
        path.add("D");
        path.add("E");

        return path;
    }

    List<Double> getIndividualCost(String start, String end) {
        List<Double> costs = new ArrayList<>();
        costs.add(21.0);
        costs.add(32.2);
        costs.add(47.1);
        costs.add(23.1);

        return costs;
    }
}
