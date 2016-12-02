//Binary Search Class to reduce code repetition
//Changes to method include: passing ListArrayBased as a parameter
public class BinarySearch {

    public static int search(String key, ListArrayBasedPlus a){
        int high = a.numItems;
        int low = 0;
        int mid = (high)/2;

        while(high - low > 0){
        Object o = a.get(mid);
            //String s =
            int compare = key.compareTo(a.get(mid).toString());
            if( compare == 0){
                return mid;
            }else if(compare < 0){
                high = mid;
                mid = (high + low)/2;
            }else{
                low = mid + 1;
                mid = (low + high)/2;
            }

        }
        if(low == a.numItems){
            return low;
        }else{
            return low;
        }

    }
}
