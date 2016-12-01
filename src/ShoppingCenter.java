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
    private QueueArrayBased normalCheckout1;
    private QueueArrayBased normalCheckout2;
    private QueueArrayBased expressCheckout;

    public ShoppingCenter() {

        custCollection = new CustomerCollection();
        stock = new InventoryList();
        normalCheckout1 = new QueueArrayBased();
        normalCheckout2 = new QueueArrayBased();
        expressCheckout = new QueueArrayBased();
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

    public ListArrayBasedPlus getListOfInventoryItems() {
        return stock.getListOfInventoryItems();
    }

    public InventoryList getInventoryList(){
        return stock;
    }

    public void addToCheckoutLines(Customer customer) {
        if (customer.getNumItems() <= 5) {

        }
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

    //increments the time that each customer has been in the store by 1

    public void completeShopping(){

    }

}
