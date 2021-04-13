// --== CS400 File Header Information ==--
// Name: Aidan Lonergan
// Email: alonergan@wisc.edu
// Team: IG Red
// Role: DataWrangler
// TA: Sid
// Lecturer: Gary Dahl
// Notes to Grader: n/a
import java.io.FileReader;
import java.util.ArrayList;

public interface LocationListInterface {
    public ArrayList<String[]> readData(FileReader fr);
    public String[] getLocation(String name);
    public int size();
}
