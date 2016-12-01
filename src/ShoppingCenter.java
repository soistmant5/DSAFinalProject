/**
 * Purpose: DSA Final Project
 * Status:
 * Last Updated:
 * Submitted:
 * @author Tom Soistmann
 * @author Joseph Messner
 * @version 2016.11.22
 */
public class ShoppingCenter {

    private ListArrayBasedPlus listOfInventoryItems;
    private ListArrayBasedPlus listOfCustomers;
    private QueueArrayBased normalCheckout1;
    private QueueArrayBased normalCheckout2;
    private QueueArrayBased expressCheckout;

    public ShoppingCenter() {
        listOfInventoryItems = new ListArrayBasedPlus();
        listOfCustomers = new ListArrayBasedPlus();
        normalCheckout1 = new QueueArrayBased();
        normalCheckout2 = new QueueArrayBased();
        expressCheckout = new QueueArrayBased();
    }

    public void addInventoryItem(int index, InventoryItem item) {
        listOfInventoryItems.add(index, item);
    }

    public void addShopper(Customer customer) {
        listOfCustomers.add(0, customer);
    }

    public void addToCheckoutLines(Customer customer) {
        if (customer.getNumItems() <= 5) {

        }
    }
    public ListArrayBasedPlus getListOfInventoryItems() {
        return listOfInventoryItems;
    }

    public ListArrayBasedPlus getListOfCustomers() {
        return listOfCustomers;
    }

    public QueueArrayBased getNormalCheckout1() {
        return normalCheckout1;
    }

    public QueueArrayBased getNormalCheckout2() {
        return normalCheckout2;
    }

    public QueueArrayBased getExpressCheckout() {
        return expressCheckout;
    }
}
