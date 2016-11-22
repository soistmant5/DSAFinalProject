/**
 * Purpose: DSA Lab 1 Problem 1
 * Status: Complete and thoroughly tested
 * Last Update: 09/19/2016
 * Submitted: 09/20/2016
 * @author Tom Soistmann
 * @version 2016.09.19
 */
public class ListArrayBasedPlus extends ListArrayBased {

    public void add(int index, Object item) {
        if (items.length == numItems) {
            Object[] tempList = new Object[items.length * 2];
            for (int i = 0; i < items.length; i++) {
                tempList[i] = items[i];
            }
            items = tempList;
        }
        super.add(index, item);
    }

    public void reverse() {
        int min = 0;
        int max = numItems - 1;
        while (min < max) {
            swap(min,max);
            min++;
            max--;
        }
    }

    private void swap(int p1, int p2) {
        Object temp = items[p1];
        items[p1] = items[p2];
        items[p2] = temp;
    }

    public String toString() {
        String returnString = "Items currently in the list:";
        for (int i = 0; i < items.length; i++) {
            returnString += (" " + items[i]);
        }
        return returnString;
    }

}
