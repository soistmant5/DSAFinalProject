/**
 * Created by Joseph on 12/1/2016.
 */
public class InventoryList {
    private ListArrayBasedPlus listOfInventoryItems;

    public InventoryList(){
        listOfInventoryItems = new ListArrayBasedPlus();
    }

    public void addInventoryItem(InventoryItem item){
        int addItemIndex = inventorySearch(item.getName());
        listOfInventoryItems.add(addItemIndex, item);
    }

    public ListArrayBasedPlus getListOfInventoryItems(){
        return listOfInventoryItems;
    }

    public InventoryItem getInventoryItem(String key){
        int getItemIndex = inventorySearch(key);
        return (InventoryItem)listOfInventoryItems.get(getItemIndex);
    }

    private int inventorySearch(Object key){
        int inventoryIndex = BinarySearch.search((String) key, listOfInventoryItems);
        return inventoryIndex;
    }

    public String toString(){
        return listOfInventoryItems.toString();
    }

    public InventoryItem[] itemsWithLowStock(){
        InventoryItem items[] = new InventoryItem[listOfInventoryItems.numItems];
        for(int i = 0; i < listOfInventoryItems.numItems; i++){
            InventoryItem item = (InventoryItem) listOfInventoryItems.get(i);
            if(item.lowStock()){
                items[i] = (InventoryItem) listOfInventoryItems.get(i);
            }
        }
        return items;
    }
}
