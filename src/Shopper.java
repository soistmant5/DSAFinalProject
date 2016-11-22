/**
 * Purpose: DSA Final Project
 * Status:
 * Last Updated:
 * Submitted:
 * @author Tom Soistmann
 * @author Joseph Messner
 * @version 2016.11.22
 */
public class Shopper {

    private String name;
    private int numItems;
    private int time;

    public Shopper(String name) {
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
}
