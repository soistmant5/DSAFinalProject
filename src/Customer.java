/**
 * Purpose: DSA Final Project
 * Status:
 * Last Updated:
 * Submitted:
 * @author Tom Soistmann
 * @author Joseph Messner
 * @version 2016.11.22
 */
public class Customer {

    private String name;
    private int numItems;
    private int time;

    public Customer(String name) {
        this.name = name;
        numItems = 0;
        time = 0;
    }

    public String getName() {
        return name;
    }

    public int getNumItems() {
        return numItems;
    }

    public int getTime() {
        return time;
    }

    public void addItem() {
        numItems++;
    }

    public void removeItem() {
        numItems--;
    }

    public void incrementTime() {
        time++;
    }

    public void resetTime() {
        time = 0;
    }

    public String toString() {
        return "Customer name: " + name + "\tItems in cart: " + numItems + "\tTime spent in store: " + time + " minutes.\n\t";
    }
}
