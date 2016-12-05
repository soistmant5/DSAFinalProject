/**
 * Purpose: DSA Final Project
 * Status:
 * Last Updated:
 * Submitted:
 * @author Tom Soistmann
 * @author Joseph Messner
 * @version 2016.11.30
 */
public class InventoryList {

    private ListArrayBasedPlus listOfInventoryItems;
    private int restockingAmount;

    public InventoryList() {
        listOfInventoryItems = new ListArrayBasedPlus();
    }

    public void addInventoryItem(InventoryItem item) {
        int index = binarySearch(item.getName(), false);
        listOfInventoryItems.add(index, item);
    }

    public ListArrayBasedPlus getListOfInventoryItems() {
        return listOfInventoryItems;
    }

    public InventoryItem searchForInventoryItem(String key) {
        int index = binarySearch(key, true);
        return (InventoryItem) listOfInventoryItems.get(index);
    }

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
