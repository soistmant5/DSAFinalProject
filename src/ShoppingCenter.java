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

    public ShoppingCenter() {
        customerList = new CustomerList();
        stock = new InventoryList();
        checkOut = new CheckOutLines();
    }

    public void addCustomer(Customer customer) {
        customerList.addCustomer(customer);
    }

    public CustomerList getCustomerList() {
        return customerList;
    }

    public void addInventoryItem(InventoryItem item) {
        stock.addInventoryItem(item);
    }

    public InventoryList getInventoryList() {
        return stock;
    }

    public CheckOutLines getCheckOutLines() {
        return checkOut;
    }

    //Adds the customer with the longest shopping time to the proper
    //Queue and returns the list the customer was added to.

    public void addCustomerToCheckOutLines(Customer customer) {
        customerList.deleteCustomer(customer);
        checkOut.addToCheckoutLines(customer);
    }

    public Customer customerLeavesStore() {
        QueueArrayBased queueToDequeue = checkOut.orderOfCheckout();
        return (Customer) queueToDequeue.dequeue();
    }
}
