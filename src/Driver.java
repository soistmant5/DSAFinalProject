import java.io.*;

/**
 * Purpose: DSA Final Project
 * Status:
 * Last Updated:
 * Submitted:
 * @author Tom Soistmann
 * @author Joseph Messner
 * @version 2016.11.22
 */
public class Driver {

    private static BufferedReader stdin;

    public Driver() {
        stdin = new BufferedReader(new InputStreamReader(System.in));
    }

    private void printMenu() {
        System.out.println("");
        System.out.println("Please select from the following options:");
        System.out.println("\t1. Customer enters Shopping Center.");
        System.out.println("\t2. Customer picks an item and places it in their shopping cart.");
        System.out.println("\t3. Customer removes an item from their shopping cart.");
        System.out.println("\t4. Customer is done shopping.");
        System.out.println("\t5. Customer checks out.");
        System.out.println("\t6. Print info about customers who are shopping.");
        System.out.println("\t7. Print info about customers in checkout lines.");
        System.out.println("\t8. Print info about items at or below re-stocking level.");
        System.out.println("\t9. Reorder an item.");
        System.out.println("\t10. Close the Shopping Center.");
        System.out.println("Please make your selection now: ");
    }
}
