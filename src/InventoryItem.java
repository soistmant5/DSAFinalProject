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
    private int minStock;

    public InventoryItem(String name, int currentStock, int requiredStock) {
        this.name = name;
        numInStock = currentStock;
        minStock = requiredStock;
    }

    public String getName() {
        return name;
    }

    public int getNumInStock() {
        return numInStock;
    }

    public int getMinStock() {
        return minStock;
    }

    public void subtractNumInStock() {
        numInStock--;
    }

    public void restock(int quantity) {
        numInStock += quantity;
    }

    public boolean hasLowStock() {
        return numInStock <= minStock;
    }

    public String toString() {
        return String.format("%-25s %-25s %-20s", "\tItem name: " + name, "Quantity in stock: " + numInStock, "Required stock: " + minStock + "\n");
    }
}
