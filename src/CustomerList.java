/**
 * Purpose: DSA Final Project
 * Status: Complete and thoroughly tested
 * Last Updated: 12/06/2016
 * Submitted: 12/06/2016
 * @author Tom Soistmann
 * @author Joseph Messner
 * @version 2016.12.06
 */
public class CustomerList {

    private ListArrayBasedPlus listOfCustomers;

    /**
     * Constructor for the CustomerList class.
     * Instantiates the ListArrayBasedPlus collection of Customer objects.
     */
    public CustomerList() {
        listOfCustomers = new ListArrayBasedPlus();
    }

    /**
     * Accessor method to get the ListArrayBasedPlus collection of Customer objects.
     *
     * @return The stored ListArrayBasedPlus collection of Customer objects
     */
    public ListArrayBasedPlus getListOfCustomers() {
        return listOfCustomers;
    }

    /**
     * Creates a new Customer object and adds it to the listOfCustomers collection.
     *
     * @param index Integer value of the index to insert a Customer object at
     * @param name String value representing the name of a customer
     */
    public void addCustomer(int index, String name) {
        Customer customer = new Customer(name);
        listOfCustomers.add(index, customer);
    }

    /**
     * Adds an already existing Customer object back into the listOfCustomers collection.
     *
     * @param index Integer value of the index to insert a Customer object at
     * @param customer The pre-existing Customer object to insert into the collection
     */
    public void addCustomer(int index, Customer customer) {
        listOfCustomers.add(index, customer);
    }

    /**
     * Searches for an existing Customer object and removes it from the listOfCustomers collection.
     *
     * @param customer Customer to delete from the listOfCustomers collection
     */
    public void deleteCustomer(Customer customer) {
        int index = binarySearch(customer.getName(), true);
        listOfCustomers.remove(index);
    }

    /**
     * Searches for an existing Customer object in the listOfCustomers collection by name.
     *
     * @param key Name of the customer
     * @return The Customer object with name that matches the search key
     */
    public Customer searchForCustomer(String key) {
        int index = binarySearch(key, true);
        return (Customer) listOfCustomers.get(index);
    }

    /**
     * Returns the Customer object found at a specified index in the listOfCustomers collection.
     *
     * @param index The index to get the Customer object at
     * @return The Customer object found at the specified index
     */
    public Customer getCustomer(int index) {
        return (Customer) listOfCustomers.get(index);
    }

    /**
     * Increments the time value for all Customer objects stored in the collection by 1.
     */
    public void incrementTime() {
        for (int i = 0; i < listOfCustomers.size(); i++) {
            Customer customer = (Customer) listOfCustomers.get(i);
            customer.incrementTime();
        }
    }

    /**
     * Iterates through the listOfCustomers collection to find the Customer object
     * that has been in the store the longest.
     *
     * @return The customer that has been in the store the longest
     */
    public Customer getCustomerWithLongestTime() {
        int indexOfLongestTime = 0;

        for (int i = 1; i < listOfCustomers.size(); i++) {
            if (getCustomer(i).getTime() > getCustomer(indexOfLongestTime).getTime()) {
                indexOfLongestTime = i;
            }
        }

        return getCustomer(indexOfLongestTime);
    }

    /**
     * When search is true, the method is used to search for and return the index of where a certain Customer object is.
     *
     * When search is false, the method is used to find the index of where to insert a Customer object
     * so that the collection remains sorted in alphabetical order by name.
     *
     * @param key The String representing the name of a customer
     * @param search True or False, whether you are trying to locate an existing object or properly insert a new one.
     * @return The index in the collection of where a Customer object is either found or inserted
     */
    public int binarySearch(String key, boolean search) {
        int high = listOfCustomers.size();
        int low = 0;
        int mid = (high) / 2;

        while (high - low > 0) {
            Customer customer = (Customer) listOfCustomers.get(mid);
            int result = key.compareTo(customer.getName());

            if (result == 0) {
                if (search) {
                  return mid;
                } else {
                    return -1;
                }
            } else if (result < 0) {
                high = mid;
                mid = (high + low) / 2;
            } else {
                low = mid + 1;
                mid = (low + high) / 2;
            }
        }

        if (search) {
            return -1;
        }

        return low;
    }
}