// --== CS400 File Header Information ==--
// Name: Ryan Toh
// Email: retoh@wisc.edu
// Team: IG Red
// Role: Frontend Developer
// TA: Sid
// Lecturer: Gary Dahl
// Notes to Grader: n/a

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;

public class TestFrontend {
    /**
     * Tests that the option to return to input menu is present.
     */
    @Test
    public void testReEnterDestinations() {
        assertEquals(
            "==================================================================================\n" +
            "Enter 'r' to re-enter destinations\n" + 
            "Enter 'x' to return to main menu\n" + 
            "==================================================================================", Frontend.printDataMenu("start", "end"));
    }

    /**
     * Tests that the main menu prints correctly.
     */
    @Test
    public void testReturnToMainMenu() { 
        assertEquals(
            "Welcome to the cheapest train path app!\n\n" + 
            "Enter ‘r’ to input start and end destinations\n" + 
            "Enter ‘q’ to quit the app\n" + 
            "=============================================", Frontend.printMainMenu());
    }

    /**
     * Tests that the input menu prints correctly.
     */
    @Test
    public void testInputMenu() { 
        assertEquals(
            "Enter start and end destinations using the\n" + 
            "format: <start-city> <end-city>\n\n" +
            "Enter ‘x’ to return to main menu\n" + 
            "=============================================", Frontend.printInputMenu());
    }

    /**
     * Checks that the data displayed (total cost) is correct.
     */
    @Test
    public void testDataMenu() { 
        assertEquals(123.4f, Frontend.printDataMenu("start", "end"));
    }

    @Test
    public void testBadInput() { 

    }
}
