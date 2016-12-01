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
    private CustomerCollection custCollection;
    private CheckOutLines checkOut;

    public ShoppingCenter() {

        custCollection = new CustomerCollection();
        stock = new InventoryList();
        checkOut = new CheckOutLines();

    }



    public void addShopper(Customer customer) {
        custCollection.addShopper(customer);
    }

    public ListArrayBasedPlus getListOfCustomers() {
        return custCollection.getListOfCustomers();
    }

    public CustomerCollection getCustCollection(){
        return custCollection;
    }

    public void addInventoryItem(InventoryItem item) {
        stock.addInventoryItem(item);
    }

    //method might not be necessary
    public ListArrayBasedPlus getListOfInventoryItems() {
        return stock.getListOfInventoryItems();
    }

    public InventoryList getInventoryList(){
        return stock;
    }

    public CheckOutLines getCheckOutLines(){
        return checkOut;
    }

    //Adds the customer with the longest shopping time to the proper
    //Queue and returns the list the customer was added to.
    public String completeShopping(){
        Customer c = custCollection.findCustomerLongestTime();
        c = (Customer) checkOut.addToCheckoutLines(c).peek();
        return c.getName();
    }

}
