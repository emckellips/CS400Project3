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
        // TODO
    }

    /**
     * This method will have the UI for the input menu.
     */
    public static void printInputMenu() {
        // TODO
    }

    /**
     * This method will have the UI for the data menu.
     */
    public static void printDataMenu() {
        // TODO
    }
}
