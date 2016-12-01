import java.io.*;

/**
 * Purpose: DSA Final Project
 * Status:
 * Last Updated:
 * Submitted:
 * @author Tom Soistmann
 * @author Joseph Messner
 * @version 2016.11.30
 */
public class Driver {

    private static BufferedReader inputData;
    private ShoppingCenter shoppingCenter;
    private ListArrayBasedPlus listOfShoppers;
    private ListArrayBasedPlus listOfInventoryItems;

    public Driver() {
        inputData = new BufferedReader(new InputStreamReader(System.in));
        shoppingCenter = new ShoppingCenter();
        listOfShoppers = new ListArrayBasedPlus();
        listOfInventoryItems = new ListArrayBasedPlus();
    }

    private String read() {

        String input = null;
        try {
            input = inputData.readLine();
            System.out.println(input);
        } catch (Exception e) {
            System.out.println("System In Error");
        }
        return input;
    }

    private void initializeInventory() {
        System.out.println("Welcome to the Soistmann-Messner Shopping Center!");
        System.out.print("To begin, enter the number of different types" +
                " of items the store has in stock: ");
        int inventoryNum = Integer.parseInt(read());

        for (int i = 1; i <= inventoryNum; i++) {
            System.out.print("\nEnter inventory item " + i + ": ");
            String itemName = read();
            System.out.print("\nNow, enter the number of this item that are currently in stock: ");
            int quantity = Integer.parseInt(read());
            System.out.print("\nFinally, enter the minimum quantity of stock of this item the store must have: \n");
            int minQuantity = Integer.parseInt(read());
            System.out.println("");
            InventoryItem item = new InventoryItem(itemName, quantity, minQuantity);
            shoppingCenter.addInventoryItem(item);
        }


        System.out.println("The store is now ready to open up for the day.");
        System.out.println("The inventory you are beginning the day with is:\n\t" + shoppingCenter.getListOfInventoryItems().toString());

    }

    private void printMenuOptions() {
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
    }

    public void masterMenu() {

        initializeInventory();

        boolean exit = false;

        do {
            try {
                printMenuOptions();

                System.out.print("Menu Selection: ");
                int select = Integer.parseInt(read());
                System.out.println("");
                switch (select) {

                    case 1:
                        customerEnters();
                        break;

                    case 2:
                        customerShopping();
                        break;

                    case 3:
                        customerRemoveItem();
                        break;

                    case 4:
                        customerFinishedShopping();
                        break;

                    case 5:
                        customerChecksOut();
                        break;

                    case 6:
                        printShoppingCustomers();
                        break;

                    case 7:
                        printCustomersInLine();
                        break;

                    case 8:
                        printRestockingInfo();
                        break;

                    case 9:
                        reOrderItem();
                        break;

                    case 10:
                        System.out.println("System Closing");
                        System.out.println("");
                        exit = true;
                        break;

                }


            } catch (Exception e) {
                System.out.println("General Error\n");

            }
        } while (!exit);
        System.exit(0);

    }

    private void customerEnters(){
        System.out.print("Please enter the name of the customer entering the store: ");
        Customer shopper = new Customer(read());
        System.out.println("");
        shoppingCenter.addShopper(shopper);
    }

    private void customerShopping(){
        System.out.print("Please enter the name of the customer who is adding to their cart: ");
        Customer c = shoppingCenter.getCustCollection().getPosOfCustomer(read());
        //Customer c = getCustomer(); //method call that searches for a customer and returns a shopper


        System.out.println("\n\n List of Inventory Items: ");
        shoppingCenter.getListOfInventoryItems().toString(); //prints out list of inventory items


        System.out.print("Now, enter the name of the inventory item they are adding: ");
        InventoryItem item = shoppingCenter.getInventoryList().getInventoryItem(read());

        // search for item
        System.out.print("\nEnter the number of items you would like to add to your shopping cart: ");
        int amount = Integer.parseInt(read());
        item.subtractNumInStock(amount);
        c.addItems(amount); //updates the total items the customer has
        shoppingCenter.getCustCollection().incrementAllTime();


    }

    private void customerRemoveItem(){
        Customer c = shoppingCenter.getCustCollection().getPosOfCustomer(read());
        System.out.print("Enter the number of items to remove from cart: ");
        c.removeItems(Integer.parseInt(read()));
        System.out.println("");
        //decrease the items in the customers shopping cart by the number of items they want to remove.
    }

    private void customerFinishedShopping(){
        String name = shoppingCenter.completeShopping();
        System.out.println("Customer: " + name + " has finished shopping and was ");

        //search for the longest time that one customer has been in the shopping center
        //based on the amount of items, the customers get added to checkout lines
    }

    private void customerChecksOut(){
        //customers get the choice to return shopping
    }

    private void printShoppingCustomers(){
        System.out.println("The customers currently shopping are:\n\t" + shoppingCenter.getListOfCustomers().toString());
    }

    private void printCustomersInLine(){
        System.out.println("The following customers are currently waiting in line to checkout:");
        if (shoppingCenter.getCheckOutLines().getNormalCheckout1().isEmpty()) {
            System.out.println("\tLine 1 is currently empty.");
        } else {
            System.out.println("\tLine 1:\n\t" + shoppingCenter.getCheckOutLines().getNormalCheckout1().toString());
        }
        if (shoppingCenter.getCheckOutLines().getNormalCheckout2().isEmpty()) {
            System.out.println("\tLine 2 is currently empty.");
        } else {
            System.out.println("\tLine 2:\n\t" + shoppingCenter.getCheckOutLines().getNormalCheckout2().toString());
        }
        if (shoppingCenter.getCheckOutLines().getExpressCheckout().isEmpty()) {
            System.out.println("\tThe express line is currently empty.");
        } else {
            System.out.println("\tExpress line:\n\t" + shoppingCenter.getCheckOutLines().getExpressCheckout().toString());
        }
    }

    private void printRestockingInfo(){
        //print the stock of the item in the shopping center
    }

    private void reOrderItem(){
        System.out.print("Please enter the name of the inventory item you'd like to reorder: ");
        String s = read();
        //search for item return the item itself
        System.out.print("\nNow, enter the quantity of this item you'd like to order: ");
        int stock = Integer.parseInt(read());
        // numInStock++
    }


}
