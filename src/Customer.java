/**
 * Purpose: DSA Final Project
 * Status: Complete and thoroughly tested
 * Last Updated: 12/06/2016
 * Submitted: 12/06/2016
 * @author Tom Soistmann
 * @author Joseph Messner
 * @version 2016.12.06
 */
public class Customer {

    private String name;
    private int numItems;
    private int time;

    /**
     * Constructor for the Customer class.
     * Initializes numItems and time to 0.
     *
     * @param name String representing the customer's name
     */
    public Customer(String name) {
        this.name = name;
        numItems = 0;
        time = 0;
    }

    /**
     * Accessor method to return the String representing the name of a Customer.
     *
     * @return The String representing a customer's name
     */
    public String getName() {
        return name;
    }

    /**
     * Accessor method to return the integer value representing number items a Customer has.
     *
     * @return The integer value representing the number of items a Customer has
     */
    public int getNumItems() {
        return numItems;
    }

    /**
     * Accessor method to return the integer value representing the amount of time a customer has
     * been in the store for.
     *
     * @return The integer value representing time spent in the store.
     */
    public int getTime() {
        return time;
    }

    /**
     * Increments the number of items a customer has by 1.
     */
    public void addItem() {
        numItems++;
    }

    /**
     * Decrements the number of items a customer has by 1.
     */
    public void removeItem() {
        numItems--;
    }

    /**
     * Increments the time a customer has spent in the store by 1.
     */
    public void incrementTime() {
        time++;
    }

    /**
     * Sets the value for the time a customer has spent in the store to 0.
     */
    public void resetTime() {
        time = 0;
    }

    /**
     * Returns a String that contains information about a customer (name, number of items, and time).
     *
     * @return String containing information about a Customer object.
     */
    public String toString() {
        return String.format("%-30s %-20s %-20s", "\tCustomer name: " + name, "Items in cart: " + numItems, "Time spent in store: " + time + " minutes.\n");
    }
}
