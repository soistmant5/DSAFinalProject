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

    private InventoryList stock;
    private CustomerList customerList;
    private CheckOutLines checkOut;
    private int restockingAmount;

    public ShoppingCenter(int reStockingAmount) {
        customerList = new CustomerList();
        stock = new InventoryList();
        checkOut = new CheckOutLines();
        this.restockingAmount = reStockingAmount;
    }

    public CustomerList getCustomerList() {
        return customerList;
    }

    public InventoryList getInventoryList() {
        return stock;
    }

    public CheckOutLines getCheckOutLines() {
        return checkOut;
    }

    public int getRestockingAmount() {
        return restockingAmount;
    }

    //Adds the customer with the longest shopping time to the proper
    //Queue and returns the list the customer was added to.

    public void addCustomerToCheckOutLines(Customer customer) {
        customerList.deleteCustomer(customer);
        checkOut.addToCheckoutLines(customer);
    }

    public QueueArrayBased findNextQueueToDequeue() {
        QueueArrayBased queueToDequeue = checkOut.orderOfCheckout();

        if (queueToDequeue == null) {
            return null;
        }
        return queueToDequeue;
    }

    public boolean hasLowStock(InventoryItem item) {
        return item.getNumInStock() <= restockingAmount;
    }

    public String printInventoryList() {
        return stock.getListOfInventoryItems().toString();
    }
}
