/**
 * Purpose: DSA Final Project
 * Status:
 * Last Updated:
 * Submitted:
 * @author Tom Soistmann
 * @author Joseph Messner
 * @version 2016.11.22
 */
public class InventoryItem {

    private String name;
    private int numInStock;

    public InventoryItem(String name, int currentStock) {
        this.name = name;
        numInStock = currentStock;
    }

    public String getName() {
        return name;
    }

    public int getNumInStock() {
        return numInStock;
    }

    public void subtractNumInStock() {
        numInStock--;
    }

    public void restock(int quantity) {
        numInStock += quantity;
    }

    public String toString() {
        return String.format("%-20s %-20s", "\tItem name: " + name, "Quantity in stock: " + numInStock + "\n");
    }
}
