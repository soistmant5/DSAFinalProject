/**
 * Purpose: DSA Final Project
 * Status: Complete and thoroughly tested
 * Last Updated: 12/06/2016
 * Submitted: 12/06/2016
 * @author Tom Soistmann
 * @author Joseph Messner
 * @version 2016.12.06
 */
public class ShoppingCenter {

    private InventoryList stock;
    private CustomerList customerList;
    private CheckOutLines checkOut;
    private int restockingAmount;

    /**
     * Constructor for class ShoppingCenter.
     * Instantiates instances of the InventoryList, CustomerList, and CheckOutLines classes.
     *
     * @param reStockingAmount The minimum required stock amount for all InventoryItem objects
     */
    public ShoppingCenter(int reStockingAmount) {
        customerList = new CustomerList();
        stock = new InventoryList();
        checkOut = new CheckOutLines();
        this.restockingAmount = reStockingAmount;
    }

    /**
     * Accessor to return the CustomerList object stored in the customerList data field.
     *
     * @return The stored instance of a CustomerList object
     */
    public CustomerList getCustomerList() {
        return customerList;
    }

    /**
     * Accessor to return the InventoryList object stored in the stock data field.
     *
     * @return The stored instance of an InventoryList object
     */
    public InventoryList getInventoryList() {
        return stock;
    }

    /**
     * Accessor to return the CheckOutLines object stored in the checkOut data field.
     *
     * @return The stored instance of a CheckOutLines object
     */
    public CheckOutLines getCheckOutLines() {
        return checkOut;
    }

    /**
     * Accessor to return the integer value of the restockingAmount data field.
     *
     * @return Integer value of restockingAmount data field.
     */
    public int getRestockingAmount() {
        return restockingAmount;
    }

    //Adds the customer with the longest shopping time to the proper
    //Queue and returns the list the customer was added to.

    /**
     * Passes the appropriate parameters to the addCustomer method in the CustomerList class. This ultimately
     * inserts a Customer object into a ListArrayBased collection of Customer objects.
     *
     * @param index The appropriate index to insert a Customer object into a ListArrayBased collection
     * @param customer The Customer object to insert into the collection
     */
    public void addCustomerToShoppingList(int index, Customer customer) {
        customerList.addCustomer(index, customer);
    }

    /**
     * Passes the appropriate parameters to the addToCheckOutLines method in the CheckOutLines class.
     * Ultimately inserts a customer who is done shopping to the appropriate QueueArrayBased checkout
     * collection, and removes them from the ListArrayBased collection of customers who are shopping.
     *
     * @param customer The Customer object to insert into a QueueArrayBased collection for checking out
     */
    public void addCustomerToCheckOutLines(Customer customer) {
        customerList.deleteCustomer(customer);
        checkOut.addToCheckoutLines(customer);
    }

    /**
     * Checks to see if a specified InventoryItem object is either at or below the restocking amount.
     *
     * @param item The InventoryItem object for which to check if it has low stock
     * @return True or false, whether or not the specified object is at or below the restocking amount
     */
    public boolean hasLowStock(InventoryItem item) {
        return item.getNumInStock() <= restockingAmount;
    }

    /**
     * Prints the information about each of the objects stored in the collection of InventoryItem objects.
     *
     * @return A String containing information of stored InventoryItem objects
     */
    public String printInventoryList() {
        return stock.getListOfInventoryItems().toString();
    }
}
