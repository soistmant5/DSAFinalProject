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
    private ShoppingCenter shoppingCenter;
    private ListArrayBasedPlus listOfShoppers;
    private ListArrayBasedPlus listOfInventoryItems;

    public Driver() {
        stdin = new BufferedReader(new InputStreamReader(System.in));
        shoppingCenter = new ShoppingCenter();
        listOfShoppers = new ListArrayBasedPlus();
        listOfInventoryItems = new ListArrayBasedPlus();

        initializeInventory();
    }

    private void initializeInventory() {
        System.out.println("Welcome to the Soistmann-Messner Shopping Center!");
        System.out.println("To begin, enter the number of inventory items the store has in stock: ");
        int inventoryNum = Integer.parseInt(readInput());
        for (int i = 0; i < inventoryNum; i++) {
            System.out.println("Enter inventory item " + (i + 1) + ": ");
            String itemName = readInput();
            System.out.println("Now, enter the number of this item that are currently in stock: ");
            int quantity = Integer.parseInt(readInput());
            System.out.println("Finally, enter the minimum quantity of stock of this item the store must have: ");
            int minQuantity = Integer.parseInt(readInput());
            InventoryItem item = new InventoryItem(itemName, quantity, minQuantity);
            shoppingCenter.addInventoryItem(i, item);
        }

        System.out.println("The store is now ready to open up for the day.");
        System.out.println("The inventory you are beginning the day with is:\n\t" + shoppingCenter.getListOfInventoryItems().toString());

        printMenu();
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

        runMenuFunctions();
    }

    private void runMenuFunctions() {
        int inputNum;
        do {
            inputNum = Integer.parseInt(readInput());
            switch (inputNum) {
                case 1:
                    System.out.println("Please enter the name of the customer entering the store: ");
                    Shopper shopper = new Shopper(readInput());
                    shoppingCenter.addShopper(shopper);
                    break;
                case 2:
                    System.out.println("Please enter the name of the customer who is adding to their cart: ");
                    // search for customer
                    System.out.println("Now, enter the name of the inventory item they are adding: ");
                    // search for item
                    break;
                case 3:
                    System.out.println("Please enter the name of the customer remvoing an item from their cart: ");
                    // search for customer
                    // numItems--
                    break;
                case 4:

                    break;
                case 5:

                    break;
                case 6:
                    System.out.println("The customers currently shopping are:\n\t" + shoppingCenter.getListOfShoppers().toString());
                    break;
                case 7:
                    System.out.println("The following customers are currently waiting in line to checkout:");
                    if (shoppingCenter.getNormalCheckout1().isEmpty()) {
                        System.out.println("\tLine 1 is currently empty.");
                    } else {
                        System.out.println("\tLine 1:\n\t" + shoppingCenter.getNormalCheckout1().toString());
                    }
                    if (shoppingCenter.getNormalCheckout2().isEmpty()) {
                        System.out.println("\tLine 2 is currently empty.");
                    } else {
                        System.out.println("\tLine 2:\n\t" + shoppingCenter.getNormalCheckout2().toString());
                    }
                    if (shoppingCenter.getExpressCheckout().isEmpty()) {
                        System.out.println("\tThe express line is currently empty.");
                    } else {
                        System.out.println("\tExpress line:\n\t" + shoppingCenter.getExpressCheckout().toString());
                    }
                    break;
                case 8:

                    break;
                case 9:
                    System.out.println("Please enter the name of the inventory item you'd like to reorder: ");
                    //search for item
                    System.out.println("Now, enter the quantity of this item you'd like to order: ");
                    // numInStock++
                    break;
                case 10:
                    exit();
                    break;
                default:
                    System.out.println("Please select a valid menu option.");
                    break;
            }
            printMenu();
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
