/**
 * Purpose: DSA Final Project
 * Status: Complete and thoroughly tested
 * Last Updated: 12/06/2016
 * Submitted: 12/06/2016
 * @author Tom Soistmann
 * @author Joseph Messner
 * @version 2016.12.06
 */
public class InventoryItem {

    private String name;
    private int numInStock;

    /**
     * Constructor for the InventoryItem class.
     *
     * @param name String representing the name of the instantiated item
     * @param currentStock Integer value of the number of this item currently in stock in the store
     */
    public InventoryItem(String name, int currentStock) {
        this.name = name;
        numInStock = currentStock;
    }

    /**
     * Accessor method to return the name of an item found in the store's inventory.
     *
     * @return The name of an inventory item found in the store's inventory
     */
    public String getName() {
        return name;
    }

    /**
     * Accessor method to return the quantity of this item currently in stock.
     *
     * @return The number of this item in stock
     */
    public int getNumInStock() {
        return numInStock;
    }

    /**
     * Decrements the number of this item that are currently in stock by 1.
     */
    public void subtractNumInStock() {
        numInStock--;
    }

    /**
     * Increments the number of this item in stock by a user specified value.
     *
     * @param quantity The quantity by which to increase the stock of this item
     */
    public void restock(int quantity) {
        numInStock += quantity;
    }

    /**
     * Returns a String representing the information of an InventoryItem object (name, quantity in stock).
     *
     * @return String containing information about an InventoryItem object
     */
    public String toString() {
        return String.format("%-20s %-20s", "\tItem name: " + name, "Quantity in stock: " + numInStock + "\n");
    }
}
