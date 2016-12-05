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

    private static BufferedReader stdin;
    private ShoppingCenter shoppingCenter;

    public Driver() {
        stdin = new BufferedReader(new InputStreamReader(System.in));
        initializeInventory();

        runMenuFunctions();
    }

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

    private void customerAddsItem(CustomerList customerList, InventoryList inventoryList) {
            if (customerList.getListOfCustomers().isEmpty()) {
                System.out.println("There are currently no customers in the store!");
            } else {
                // possible infinite loop
                System.out.print("Please enter the name of the customer who is adding an item to their cart: ");
                int index;
                do {
                    index = customerList.binarySearch(read(), true);
                    if (index < 0) {
                        System.out.println("Name not found in store. try Again");
                    }
                } while (index < 0);
                Customer customer = customerList.getCustomer(index);
                //System.out.println("\nPlease select from the current list of Inventory Items: ");
                //System.out.println(inventoryList.getListOfInventoryItems().toString()); //prints out list of inventory items

                System.out.print("Now, enter the name of the inventory item the customer is putting in their cart: ");
                String object = read();
                InventoryItem item = inventoryList.searchForInventoryItem(object);

                    System.out.println(object + " not int the shopping center");

                if (item.getNumInStock() == 0) {
                    System.out.println("Unfortunately, there is no more of this product available. Please order more.");
                } else {
                    customer.addItem(); //updates the total items the customer has
                    customerList.incrementTime();
                    System.out.println(customer.getName() + " now has " + customer.getNumItems() + " item(s) in their cart.");
                }
            }

    }

    private void customerRemovesItem(CustomerList customerList) {
        if (customerList.getListOfCustomers().isEmpty()) {
            System.out.println("There are currently no customers in the store!");
        } else {
            System.out.println("Please enter the name of the customer who is removing an item from their cart:");
            Customer customer = customerList.searchForCustomer(read());
            customer.removeItem();
            customerList.incrementTime();
            //decrease the items in the customers shopping cart by the number of items they want to remove.
        }
    }

    private void customerFinishedShopping(CustomerList customerList) {
        if (customerList.getListOfCustomers().isEmpty()) {
            System.out.println("There are currently no customers in the store!");
        } else {
            Customer customer = customerList.getCustomerWithLongestTime();

            if (customer.getNumItems() == 0) {
                System.out.println(customer.getName() + " has been in the store the longest (" + customer.getTime() + "minutes) , but still has an empty cart.");
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
            //search for the longest time that one customer has been in the shopping center
            //based on the amount of items, the customers get added to checkout lines
        }
    }

    private void customerChecksOut(CheckOutLines checkoutLines) {
        QueueArrayBased currentCheckout = checkoutLines.orderOfCheckout();
        Customer customer = (Customer) currentCheckout.peek();

        if (customer == null) {
            System.out.println("There are no customers waiting to checkout at this time.");
        } else {
            System.out.println(customer.getName() + " is next in line to checkout. Would you still like them to leave? (Y/N)");
            String response = read();
            if (response.equals("N")) {
                currentCheckout.dequeue();
                int index = shoppingCenter.getCustomerList().binarySearch(customer.getName(), false);
                shoppingCenter.getInventoryList().getListOfInventoryItems().add(index, customer);
                customer.resetTime();
                System.out.println("\n" + customer.getName()+ " has left the checkout line and has gone back to shopping.");
            } else if (response.equals("Y")) {
                currentCheckout.dequeue();
                System.out.println(customer.getName() + " has checked out and is now leaving the store.");
            }
        }
    }

    private void printShoppingCustomers(ListArrayBasedPlus customerList) {
        if (customerList.isEmpty()) {
            System.out.println("There are currently no customers in the store.");
        } else {
            System.out.println("There are currently " + customerList.size() + " customers shopping:");
            System.out.println(customerList.toString());
        }
    }

    private void printCustomersInLine(CheckOutLines checkOutLines) {
        QueueArrayBased normalCheckout1 = checkOutLines.getNormalCheckout1();
        QueueArrayBased normalCheckout2 = checkOutLines.getNormalCheckout2();
        QueueArrayBased expressCheckout = checkOutLines.getExpressCheckout();

        System.out.println("The following customers are currently waiting in line to checkout:");

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
     *
     */
    private void reOrderItem(){
        try {
            System.out.print("Please enter the name of the inventory item you'd like to order stock of: ");
            String s = read();
            InventoryItem item = shoppingCenter.getInventoryList().searchForInventoryItem(s);
            //search for item return the item itself
            System.out.print("\nNow, enter the quantity of this item you'd like to order: ");
            int quantity = Integer.parseInt(read());
            item.restock(quantity);
            System.out.println("\n" + s + " was restocked by " + quantity + " units.");
        }catch(Exception e){
            System.out.println("Inventory Item name incorrect");
        }
    }

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
