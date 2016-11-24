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
    private ListArrayBasedPlus listOfShoppers;
    private ListArrayBasedPlus listOfInventoryItems;

    public Driver() {
        stdin = new BufferedReader(new InputStreamReader(System.in));
        listOfShoppers = new ListArrayBasedPlus();
        listOfInventoryItems = new ListArrayBasedPlus();
    }

    private void initializeInventory() {
        System.out.println("Welcome to the Soistmann-Messner Shopping Center!");
        System.out.println("To begin, enter the number of inventory items the store has in stock: ");
        int inventoryNum = Integer.parseInt(readInput());
        for (int i = 0; i <= inventoryNum; i++) {
            System.out.println("Enter inventory item " + (inventoryNum + 1) + ": ");
            String item = readInput();
            listOfInventoryItems.add(i, item);
        }

        System.out.println("The store is ready to open up for the day.");
        System.out.println("")
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

    private void runMenuFunctions() {
        int inputNum;
        do {
            inputNum = Integer.parseInt(readInput());
            switch (inputNum) {
                case 1:
                    System.out.println("Please enter the name of the customer entering the store: ");
                    String customerName = readInput();
                    listOfShoppers.add(listOfShoppers.size() - 1, customerName);
                    break;
                case 2:

                    break;
                case 3:

                    break;
                case 4:

                    break;
                case 5:

                    break;
                case 6:

                    break;
                case 7:

                    break;
                case 8:

                    break;
                case 9:

                    break;
                case 10:
                    exit();
                    break;
                default:
                    System.out.println("Please select a valid menu option.");
                    break;
            }
        } while (inputNum != 10);
    }

    private void exit() {
        System.out.println("You have successfully quit the program.");
        System.exit(1);
    }

    private String readInput() {
        String result = "";
        try {
            result = stdin.readLine().trim();
            System.out.println(result);
        } catch (Exception e) {
            System.out.println("Error reading input.");
        }
        return result;
    }

}
