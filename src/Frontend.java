// --== CS400 File Header Information ==--
// Name: Ryan Toh
// Email: retoh@wisc.edu
// Team: IG Red
// Role: Frontend Developer
// TA: Sid
// Lecturer: Gary Dahl
// Notes to Grader: This uses the DummyBackend class due to our backend developer not completing his part.

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Frontend {
    private static DummyBackend b;

    private static String appHeader =
        "=============================================\n" +
        "           Cheapest Train Path App           \n" +
        "=============================================";

    /**
     * Main app loop.
     * 
     * @param args
     */
    public static void main(String[] args) {
        // init input
        Scanner scnr = new Scanner(System.in);
        boolean done = false;

        try {
            b = new DummyBackend(new FileReader("src\\NodeDataP3.csv"));
            String input = "";

            // main app loop
            while (!done) {
                if (input.isBlank()) {
                    printMainMenu();
                }
                
                input = scnr.next();

                switch (input) {
                    case "r": // enter the input mode
                        printInputMenu();
                        if (scnr.hasNext("x")) {
                            scnr.next();
                            printMainMenu();
                        } else {
                            String userStart = scnr.next();
                            String userEnd = scnr.next();
                            printDataMenu(userStart, userEnd);
                        }
                        break;
                    
                    case "q": // quit the app
                        System.out.println("Goodbye.");
                        scnr.close();
                        done = true;
                        break;
                    
                    case "x": // return to main menu
                        printMainMenu();
                        break;
                    
                    default: // any other input will just display the main menu
                        printMainMenu();
                        break;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File was not found.");
        } catch (IOException e) {
            System.out.println("Error occured while parsing file.");
        }
    }

    /**
     * This method will have the UI for the main menu.
     */
    public static String printMainMenu() {
        String str = 
            "Welcome to the cheapest train path app!\n\n" +
            "Enter ‘r’ to input start and end destinations\n" +
            "Enter ‘q’ to quit the app\n" + 
            "=============================================";

        System.out.println(appHeader);
        System.out.println(str);

        return str;
    }

    /**
     * This method will have the UI for the input menu.
     */
    public static String printInputMenu() {
        String str = 
            "Enter start and end destinations using the\n" + 
            "format: <start-city> <end-city>\n\n" +
            "Enter ‘x’ to return to main menu\n" + 
            "=============================================";

        System.out.println(appHeader);
        System.out.println(str);

        return str;
    }

    /**
     * This method will have the UI for the data menu.
     * 
     * @param userStart will contain the user input for start destination
     * @param userEnd will contain the user input for end destination
     */
    public static String printDataMenu(String start, String end) {
        final int TOTAL_COST_WIDTH = 27;

        String totalCost = String.valueOf(b.getTotalCosts(start, end));
        totalCost = "$ " + totalCost;

        // add proper spacing for total cost
        int spacing = (TOTAL_COST_WIDTH - totalCost.length()) / 2;
        if ((TOTAL_COST_WIDTH - totalCost.length()) % 2 == 1) {// if odd spacing, we add the extra spacing to the right
            totalCost += " ";
        }
        for (int i = 0; i < spacing; i++) {
            totalCost = " " + totalCost + " ";
        }

        // get locations and costs
        List<String> locations = b.getPath(start, end);
        List<Double> individualCosts = b.getIndividualCost(start, end);

        System.out.println("=================================================================================="); // table width: 83
        System.out.println("|                              Cheapest Train Data                               |");
        System.out.println("==================================================================================");
        System.out.println("|                     Total Cost                     |"    +   totalCost   +    "|");
        System.out.println("----------------------------------------------------------------------------------");
        System.out.println("|                  City                  |  Individual Cost  |  Cumulative Cost  |");
        System.out.println("----------------------------------------------------------------------------------");
        
        float cumulativeCost = 0.0f;
        final int CITY_WIDTH = 40;
        final int COST_WIDTH = 19; 
        for (int i = 1; i < locations.size(); i++) {
            System.out.print("|");

            // add city column
            String cityCol = " " + locations.get(i - 1) + " to " + locations.get(i);
            int cityColLength = cityCol.length();
            // add spacing for city column
            for (int s = 0; s < CITY_WIDTH - cityColLength; s++) {
                cityCol += " ";
            }

            System.out.print(cityCol);
            System.out.print("|");

            // add individual cost column
            String individualCostCol = " $ " + individualCosts.get(i - 1);
            int individualCostColLength = individualCostCol.length();
            // add spacing for individual cost column
            for (int s = 0; s < COST_WIDTH - individualCostColLength; s++) {
                individualCostCol += " ";
            }

            System.out.print(individualCostCol);
            System.out.print("|");

            // add cumulative cost column
            cumulativeCost += individualCosts.get(i - 1);
            String cumulativeCostCol = " $ " + cumulativeCost;
            int cumulativeCostColLength = cumulativeCostCol.length();
            // add spacing for cumulative cost column
            for (int s = 0; s < COST_WIDTH - cumulativeCostColLength; s++) {
                cumulativeCostCol += " ";
            }

            System.out.print(cumulativeCostCol);
            System.out.println("|");
        }

        String str = 
            "==================================================================================\n" +
            "Enter 'r' to re-enter destinations\n" + 
            "Enter 'x' to return to main menu\n" + 
            "==================================================================================";
        System.out.println(str);

        return str;
    }
}
