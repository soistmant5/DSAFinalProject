/**
 * Purpose: DSA Final Project
 * Status: Complete and thoroughly tested
 * Last Updated: 12/06/2016
 * Submitted: 12/06/2016
 * @author Tom Soistmann
 * @author Joseph Messner
 * @version 2016.12.06
 */
public class InventoryList {

    private ListArrayBasedPlus listOfInventoryItems;

    /**
     * Constructor for the InventoryList class.
     * Instantiates the ListArrayBasedPlus collection of InventoryItem objects.
     */
    public InventoryList() {
        listOfInventoryItems = new ListArrayBasedPlus();
    }

    /**
     * Adds an InventoryItem object into the listOfInventoryItems collection.
     * Utilizes binary search to sort the collection in alphabetical order by name.
     *
     * @param item The InventoryItem to insert into the listOfInventoryItems collection.
     */
    public void addInventoryItem(InventoryItem item) {
        int index = binarySearch(item.getName(), false);
        listOfInventoryItems.add(index, item);
    }

    /**
     * Accessor method to return the listOfInventoryItems collection.
     *
     * @return The ListArrayBasedPlus listOfInventoryItems collection
     */
    public ListArrayBasedPlus getListOfInventoryItems() {
        return listOfInventoryItems;
    }

    /**
     * Utilizes binary search to search for an InventoryItem in the listOfInventoryItems collection by name.
     *
     * @param key String representing the name of an inventory item
     * @return The resulting InventoryItem object
     */
    public InventoryItem searchForInventoryItem(String key) {
        int index = binarySearch(key, true);
        return (InventoryItem) listOfInventoryItems.get(index);
    }

    /**
     * When search is true, the method is used to search for and return the index of where a certain
     * InventoryItem object is.
     *
     * When search is false, the method is used to find the index of where to insert an InventoryItem object
     * so that the collection remains sorted in alphabetical order by name.
     *
     * @param key The String representing the name of an inventory item
     * @param search True or False, whether you are trying to locate an existing object or properly insert a new one.
     * @return The index in the collection of where an InventoryItem object is either found or inserted
     */
    public int binarySearch(String key, boolean search) {
        int high = listOfInventoryItems.size();
        int low = 0;
        int mid = (high) / 2;

        while (high - low > 0) {
            InventoryItem item = (InventoryItem) listOfInventoryItems.get(mid);
            int result = key.compareTo(item.getName());

            if (result == 0) {
                return mid;
            } else if (result < 0) {
                high = mid;
                mid = (high + low) / 2;
            } else {
                low = mid + 1;
                mid = (low + high) / 2;
            }
        }

        if(search){
            return -1;
        }

        return low;
    }
}
