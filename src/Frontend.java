// --== CS400 File Header Information ==--
// Name: Ryan Toh
// Email: retoh@wisc.edu
// Team: IG Red
// Role: Frontend Developer
// TA: Sid
// Lecturer: Gary Dahl
// Notes to Grader: n/a

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Frontend {
    private static Backend b;

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
        BufferedReader br;

        try {
            br = new BufferedReader(new FileReader("temp.csv"));
            b = new Backend(br);
            String input = "";

            // main app loop
            while (!done) {
                if (input.isBlank() || input.contentEquals("x")) {
                    printMainMenu();
                }
                
                input = scnr.next();

                switch (input) {
                    case "r": // enter the input mode
                        printInputMenu();
                        // TODO: input menu loop
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
    public static void printMainMenu() {
        System.out.println(appHeader);
        System.out.println("Welcome to the cheapest train path app!");
        System.out.println();
        System.out.println("Enter ‘r’ to input start and end destinations");
        System.out.println("Enter ‘q’ to quit the app");
        System.out.println("=============================================");
    }

    /**
     * This method will have the UI for the input menu.
     */
    public static void printInputMenu() {
        System.out.println(appHeader);
        System.out.println("Enter start and end destinations using the format: <start-city> <end-city>");
        System.out.println();
        System.out.println("Enter ‘x’ to return to main menu");
        System.out.println("=============================================");
    }

    /**
     * This method will have the UI for the data menu.
     * 
     * @param userStart will contain the user input for start destination
     * @param userEnd will contain the user input for end destination
     */
    public static void printDataMenu(String userStart, String userEnd) {
        final int TOTAL_COST_WIDTH = 27;

        String totalCost = String.valueOf(b.getTotalCosts(userStart, userEnd));
        totalCost = "$" + totalCost;

        // add proper spacing for total cost
        int spacing = (TOTAL_COST_WIDTH - totalCost.length()) / 2;
        if ((TOTAL_COST_WIDTH - totalCost.length()) % 2 == 1) {// if odd spacing, we add the extra spacing to the right
            totalCost += " ";
        }
        for (int i = 0; i < spacing; i++) {
            totalCost = " " + totalCost + " ";
        }

        // get locations and costs
        ArrayList<LocationListInterface> locations = b.getPath(userStart, userEnd);
        ArrayList<Integer> individualCosts = b.getIndividualCost(userStart, userEnd);

        System.out.println("=================================================================================="); // table width: 83
        System.out.println("|                              Cheapest Train Data                               |");
        System.out.println("==================================================================================");
        System.out.println("|                     Total Cost                     |"    +   totalCost   +    "|");
        System.out.println("----------------------------------------------------------------------------------");
        System.out.println("|                  City                  |  Individual Cost  |  Cumulative Cost  |");
        System.out.println("----------------------------------------------------------------------------------");
        
        int cumulativeCost = 0;
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
            String individualCostCol = " $" + individualCosts.get(i - 1);
            int individualCostColLength = individualCostCol.length();
            // add spacing for individual cost column
            for (int s = 0; s < COST_WIDTH - individualCostColLength; s++) {
                individualCostCol += " ";
            }

            System.out.print(individualCostCol);
            System.out.print("|");

            // add cumulative cost column
            cumulativeCost += individualCosts.get(i - 1);
            String cumulativeCostCol = " $" + cumulativeCost;
            int cumulativeCostColLength = cumulativeCostCol.length();
            // add spacing for cumulative cost column
            for (int s = 0; s < COST_WIDTH - cumulativeCostColLength; s++) {
                cumulativeCostCol += " ";
            }

            System.out.print(cumulativeCostCol);
            System.out.println("|");
        }

        System.out.println("==================================================================================");
        System.out.println();
        System.out.println("Enter 'r' to re-enter destinations");
        System.out.println("Enter 'x' to return to main menu");
        System.out.println("==================================================================================");
    }
}
