import java.io.*;

/**
 * Purpose: DSA Final Project
 * Status: Complete and thoroughly tested
 * Last Updated: 12/06/2016
 * Submitted: 12/06/2016
 * @author Tom Soistmann
 * @author Joseph Messner
 * @version 2016.12.06
 */
public class Driver {

    private static BufferedReader stdin;
    private ShoppingCenter shoppingCenter;

    /**
     * Constructor for class Driver
     * Instantiates the BufferedReader
     * calls initializeInventory() to instantiate all other
     * collections and objects
     */
    public Driver() {
        stdin = new BufferedReader(new InputStreamReader(System.in));
        initializeInventory();

        runMenuFunctions();
    }

    /**
     * Instantiates the collection of type InventoryItem based on user input.
     * Instantiates the ShoppingCenter object.
     *
     * Asks the user to specify the number of InventoryItem objects to be stored in the collection, and then
     * the name and quantity of each item which are passed as parameters for the construction of a new InventoryItem object.
     *
     * Utilizes the binary search method to sort the objects into alphabetical order by name.
     *
     * Also, the user is asked to specify a minimum quantity, or restocking amount, that is used for all of the items.
     */
    private void initializeInventory() {
        System.out.println("Welcome to the Soistmann-Messner Shopping Center!");
        System.out.print("To begin, enter the number of different types of items the store has in stock: ");
        int inventoryNum = Integer.parseInt(read());

        System.out.println("Next, enter the restocking amount for all of the inventory items: ");
        int restockingAmount = Integer.parseInt(read());

        shoppingCenter = new ShoppingCenter(restockingAmount);

        for (int i = 1; i <= inventoryNum; i++) {
            System.out.print("\nEnter inventory item " + i + ": ");
            String itemName = read();

            System.out.print("\nNow, enter the number of this item that are currently in stock: ");
            int quantity = Integer.parseInt(read());

            InventoryItem item = new InventoryItem(itemName, quantity);
            shoppingCenter.getInventoryList().addInventoryItem(item);
        }

        System.out.println("Finally, select the checkout that will check customers out first. (express, regular1, regular2): ");
        shoppingCenter.getCheckOutLines().checkOutStart(read());

        System.out.println("");
        System.out.println("The store is now ready to open up for the day.");
        System.out.println("The inventory you are beginning the day with is:\n" + shoppingCenter.printInventoryList());
    }

    /**
     * Prints the user interface.
     */
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

    /**
     * Reads in the user input after making a menu selection.
     * Switch statement on user input determines which functionality to provide.
     */
    public void runMenuFunctions() {
        boolean exit = false;

        do {
            try {
                printMenuOptions();

                System.out.print("Make your menu selection now: ");
                int select = Integer.parseInt(read());
                System.out.println("");

                switch (select) {
                    case 1:
                        customerEnters();
                        break;

                    case 2:
                        customerAddsItem(shoppingCenter.getCustomerList(), shoppingCenter.getInventoryList());
                        break;

                    case 3:
                        customerRemovesItem(shoppingCenter.getCustomerList());
                        break;

                    case 4:
                        customerFinishedShopping(shoppingCenter.getCustomerList());
                        break;

                    case 5:
                        customerChecksOut(shoppingCenter.getCheckOutLines());
                        break;

                    case 6:
                        printShoppingCustomers(shoppingCenter.getCustomerList().getListOfCustomers());
                        break;

                    case 7:
                        printCustomersInLine(shoppingCenter.getCheckOutLines());
                        break;

                    case 8:
                        printRestockingInfo(shoppingCenter.getInventoryList().getListOfInventoryItems());
                        break;

                    case 9:
                        reOrderItem();
                        break;

                    case 10:
                        System.out.println("You have successfully quit the program.");
                        exit = true;
                        break;
                }
            } catch (Exception e) {
                System.out.println("An error has occurred. Please try again.");
            }
        } while (!exit);
        System.exit(0);
    }

    /**
     * Asks users to specify a name for a new Customer.
     *
     * Checks if there is already a Customer object with that name, if not then
     * a new Customer object is created, and added to its proper spot in the
     * collection of type Customer based on alphabetical order by name.
     */
    private void customerEnters() {
        System.out.print("Please enter the name of the customer entering the store: ");
        String name;
        int index;

        do {
            name = read();
            index = shoppingCenter.getCustomerList().binarySearch(name, false);
            if (index < 0) {
                System.out.println(name + " is already in the store! Please enter another customer: ");
            }
        } while (index < 0);

        System.out.println("");
        shoppingCenter.getCustomerList().addCustomer(index,name);
    }

    /**
     * Lets the user specify the name of a Customer for which they'd like to increase the amount of items they have,
     * followed by the name of a specific InventoryItem.
     *
     * If both objects exist, then the quantity of items this Customer has increases by 1, and the
     * quantity of items for this InventoryItem decreases by 1.
     *
     * If successful, the amount of time for all customers in the store increases by 1.
     *
     * If the specified Customer is not found in the collection, the user is immediately asked for a
     * different name.
     *
     * If the specified InventoryItem is either out of stock or not found in the collection, the appropriate
     * error message is printed and the user must re-run the method to try again.
     *
     * @param customerList An instance of CustomerList which holds a ListArrayBased collection of Customer objects
     * @param inventoryList An instance of InventoryList which holds a ListArrayBased collection of InventoryItem objects
     */
    private void customerAddsItem(CustomerList customerList, InventoryList inventoryList) {
            if (customerList.getListOfCustomers().isEmpty()) {
                System.out.println("There are currently no customers in the store!");
            } else {
                System.out.print("Please enter the name of the customer who is adding an item to their cart: ");
                int index = searchByName(customerList);
                Customer customer = customerList.getCustomer(index);

                System.out.print("Now, enter the name of the inventory item the customer is putting in their cart: ");
                String object = read();
                InventoryItem item = inventoryList.searchForInventoryItem(object);

                if (item.getNumInStock() == 0) {
                    System.out.println("Unfortunately, there is no more of this product available. Please order more.");
                } else {
                    customer.addItem(); //updates the total items the customer has
                    customerList.incrementTime();
                    item.subtractNumInStock();
                    System.out.println(customer.getName() + " now has " + customer.getNumItems() + " item(s) in their cart.");
                }
            }

    }

    /**
     * Lets the user specify the name of a Customer for which they'd like to decrease the number of items they have.
     * If the Customer object does exist, their number of items is decremented by 1.
     *
     * If the specified Customer is not found in the collection, the user is immediately asked for a
     * different name.
     *
     * If successful, the amount of time for all customers in the store increases by 1.
     *
     * Note: Users are not asked for a specific InventoryItem, and InventoryItems are not restocked after
     * the customer successfully removes an item from their cart.
     *
     * @param customerList An instance of CustomerList which holds a ListArrayBased collection of Customer objects
     */
    private void customerRemovesItem(CustomerList customerList) {
        if (customerList.getListOfCustomers().isEmpty()) {
            System.out.println("There are currently no customers in the store!");
        } else {
            System.out.println("Please enter the name of the customer who is removing an item from their cart:");
            int index = searchByName(customerList);
            Customer customer = customerList.getCustomer(index);

            if (customer.getNumItems() == 0) {
                System.out.println(customer.getName() + " currently has zero items in their cars; there is nothing to remove.");
            } else {
                customer.removeItem();
                customerList.incrementTime();
                System.out.println(customer.getName() + " now has " + customer.getNumItems() + " item(s) in their cart.");
            }
        }
    }

    /**
     * If the collection of type Customer is populated, the one who has spent the longest amount of time in the
     * store is searched for. If this Customer has no items, the user is asked if they'd like to either keep them
     * in the store or remove them. If they are kept in the store, their time is reset to zero.
     *
     * Otherwise, they are added to the appropriate QueueArrayBased collection in which they wait to
     * checkout and ultimately leave the store.
     *
     * @param customerList An instance of CustomerList which holds a ListArrayBased collection of Customer objects
     */
    private void customerFinishedShopping(CustomerList customerList) {
        if (customerList.getListOfCustomers().isEmpty()) {
            System.out.println("There are currently no customers in the store!");
        } else {
            Customer customer = customerList.getCustomerWithLongestTime();

            if (customer.getNumItems() == 0) {
                System.out.println(customer.getName() + " has been in the store the longest (" + customer.getTime() + " minutes), but still has an empty cart.");
                System.out.print("Would you like them to leave the store with no items? (Y/N): ");
                String response = read();

                if (response.equals("N")) {
                    customer.resetTime();
                    System.out.println("\n" + customer.getName() + " will remain in the store.");
                } else if (response.equals("Y")) {
                    shoppingCenter.getCustomerList().deleteCustomer(customer);
                    System.out.println(customer.getName() + " has left the store.");
                }
            } else {
                shoppingCenter.addCustomerToCheckOutLines(customer);
                System.out.println("After " + customer.getTime() + " minutes, " + customer.getName() + " has finished shopping and is now waiting in the checkout lines.");
            }
        }
    }

    /**
     * If any of the three checkout Queues are populated with Customer objects, then the Customer at the front of the next queue
     * is grabbed using peek().
     *
     * Before removing this Customer from their queue, the user is asked if they'd like to do so or not. If not, they are placed back
     * into the collection of Customers objects that are considered to be shopping, with their time reset to 0. Otherwise they are
     * removed using dequeue() and this completely removed from all collections.
     *
     * @param checkoutLines An instance of CheckOutLines which holds three QueueArrayBased collections of Customer objects
     */
    private void customerChecksOut(CheckOutLines checkoutLines) {
        QueueArrayBased currentCheckout = checkoutLines.orderOfCheckout();

        if (currentCheckout == null) {
            System.out.println("There are no customers waiting to checkout at this time.");
        } else {
            Customer customer = (Customer) currentCheckout.peek();
            System.out.println(customer.getName() + " is next in line to checkout. Would you still like them to leave? (Y/N)");
            String response = read();

            if (response.equals("N")) {
                int index = shoppingCenter.getCustomerList().binarySearch(customer.getName(), false);
                shoppingCenter.addCustomerToShoppingList(index, customer);
                customer.resetTime();
                currentCheckout.dequeue();
                System.out.println("\n" + customer.getName()+ " has left the checkout line and has gone back to shopping.");
            } else if (response.equals("Y")) {
                currentCheckout.dequeue();
                System.out.println(customer.getName() + " has checked out and is now leaving the store.");
            }
        }
    }

    /**
     * Prints the information (name, number of items, time) about any Customer objects currently stored in the
     * ListArrayBased collection of customers who are currently shopping.
     *
     * @param customerList A ListArrayBased collection of Customer objects
     */
    private void printShoppingCustomers(ListArrayBasedPlus customerList) {
        if (customerList.isEmpty()) {
            System.out.println("There are currently no customers in the store!");
        } else {
            System.out.println("There are currently " + customerList.size() + " customers shopping:");
            System.out.println(customerList.toString());
        }
    }

    /**
     * Prints the information (name, number of items, time) about any Customer objects currently stored in any of the
     * three QueueArrayBased checkout collections.
     *
     * @param checkOutLines An instance of CheckOutLines which holds three QueueArrayBased collections of Customer objects
     */
    private void printCustomersInLine(CheckOutLines checkOutLines) {
        QueueArrayBased normalCheckout1 = checkOutLines.getNormalCheckout1();
        QueueArrayBased normalCheckout2 = checkOutLines.getNormalCheckout2();
        QueueArrayBased expressCheckout = checkOutLines.getExpressCheckout();

        if (normalCheckout1.isEmpty()) {
            System.out.println("\tLine 1 is currently empty.\n");
        } else {
            System.out.println("\tLine 1:\n" + normalCheckout1.toString());
        }

        if (normalCheckout2.isEmpty()) {
            System.out.println("\tLine 2 is currently empty.\n");
        } else {
            System.out.println("\tLine 2:\n" + normalCheckout2.toString());
        }

        if (expressCheckout.isEmpty()) {
            System.out.println("\tExpress line is currently empty.\n");
        } else {
            System.out.println("\tExpress line:\n" + expressCheckout.toString());
        }
    }

    /**
     * Prints the information (name, quantity in stock) about any InventoryItem objects for which their current
     * stock is less than or equal to the initially specified restocking amount.
     *
     * @param inventoryList A ListArrayBased collection of InventoryItem objects
     */
    private void printRestockingInfo(ListArrayBasedPlus inventoryList) {
        String result = "";

        for (int i= 0; i < inventoryList.size(); i++) {
            InventoryItem item = (InventoryItem) inventoryList.get(i);

            if (shoppingCenter.hasLowStock(item)) {
                result += String.format("%-25s %-25s", "\tItem name: " + item.getName(), "Quantity in stock: " + item.getNumInStock() + "\n");
            }
        }

        if (result.equals("")) {
            System.out.println("There currently no items that are at or below their respective restocking level.");
        } else {
            System.out.println("The following items are currently at or below the restocking level:");
            System.out.println(result);
        }
    }

    /**
     * Lets users specify the name of an InventoryItem for which they'd like to restock. If the object exists, its current stock
     * is incremented by a user specified quantity.
     */
    private void reOrderItem() {
        try {
            System.out.print("Please enter the name of the inventory item you'd like to order stock of: ");
            String s = read();
            InventoryItem item = shoppingCenter.getInventoryList().searchForInventoryItem(s);
            System.out.print("\nNow, enter the quantity of this item you'd like to order: ");
            int quantity = Integer.parseInt(read());
            item.restock(quantity);
            System.out.println("\n" + s + " was restocked by " + quantity + " units.");
        } catch(Exception e) {
            System.out.println("The store does not carry the specified inventory item. Please try again.");
        }
    }

    /**
     * Utilizes the binary search method to find and return the index of a user specified Customer object.
     * While the Customer object is not found, the user is immediately asked to specify another name.
     *
     * @param customerList An instance of CustomerList which holds a ListArrayBased collection of Customer objects
     * @return An integer for the index of the Customer object that was searched for
     */
    private int searchByName(CustomerList customerList) {
        int index;

        do {
            index = customerList.binarySearch(read(), true);
            if (index < 0) {
                System.out.println("The specified customer is either not in the store or is done shopping. Enter a different name: ");
            }
        } while (index < 0);

        return index;
    }

    /**
     * Utilizes the BufferedReader and InputStreamReader classes to read in user input from the keyboard.
     *
     * @return User input scanned in from the keyboard
     */
    private String read() {
        String input = null;

        try {
            input = stdin.readLine();
            System.out.println(input);
        } catch (Exception e) {
            System.out.println("Error reading input.");
        }

        return input;
    }
}
