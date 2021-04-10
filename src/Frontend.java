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
import java.util.Scanner;

public class Frontend {
    private static Backend b;

    private static String appHeader =
        "=============================================\n" +
        "           Cheapest Train Path App           \n" +
        "=============================================";

    /**
     * Main app loop.
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
                    case "s": // enter the input mode
                        printInputMenu();
                        // TODO: input menu loop
                        break;
                    
                    case "q": // quit the app
                        System.out.println("Goodbye.");
                        scnr.close();
                        done = true;
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
        System.out.println("Enter ‘s’ to input start and end destinations");
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
     */
    public static void printDataMenu() {
        final int WIDTH = 27;
        String totalCost = String.valueOf(b.getTotalCosts());
        totalCost = "$" + totalCost;

        // add proper spacing for total cost
        int spacing = (27 - totalCost.length()) / 2;
        if ((27 - totalCost.length()) % 2 == 1) {// odd spacing we add the extra spacing to the right
            totalCost += " ";
        }
        for (int i = 0; i < spacing; i++) {
            totalCost = " " + totalCost + " ";
        }

        System.out.println(appHeader);
        System.out.println();
        System.out.println("----------------------------------------------------------------------------------");
        System.out.println("|                   Total Cost                       |"    +   totalCost   +    "|");
        System.out.println("----------------------------------------------------------------------------------");
        System.out.println("|          City          |      Individual Cost      |      Cumulative Cost      |");
        System.out.println("----------------------------------------------------------------------------------");
        // TODO data for the rest of the table
    }
}
