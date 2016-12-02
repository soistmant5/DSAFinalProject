/**
 * Created by Joseph on 11/30/2016.
 */
public class CustomerCollection {
    private ListArrayBasedPlus listOfCustomers;


    public CustomerCollection(){
        listOfCustomers = new ListArrayBasedPlus();
    }

    public void addShopper(Customer customer){
        //int addCustIndex = customerSearch(customer.getName());
        int addCustIndex = searchCustomer(customer.getName());
        System.out.println("Index position customer should be placed (TEST): " + addCustIndex);
        listOfCustomers.add(addCustIndex, customer);
    }

    public ListArrayBasedPlus getListOfCustomers(){
        return listOfCustomers;
    }

    public Customer getPosOfCustomer(String key){
        //int index = customerSearch(key);
        int index = searchCustomer(key);
        return (Customer) listOfCustomers.get(index);
    }

    private int customerSearch(Object key){
        int customerIndex = BinarySearch.search((String) key, listOfCustomers);
        return customerIndex;
    }

    public Customer getCustomer(int index) {
        return (Customer) listOfCustomers.get(index);
    }

    public void incrementAllTime(){

        for(int i = 0; i < listOfCustomers.numItems; i++){
            Customer c = (Customer) listOfCustomers.get(i);
            c.incrementTime();
        }
    }

    public Customer findCustomerLongestTime(){
        int longestTime = getCustomer(0).getTime();
        int index = 0;
        for(int i = 1; i <= listOfCustomers.numItems; i++){
            int time = getCustomer(i).getTime();
            if(longestTime < time){
                longestTime = time;
                index = i;
            }
        }
        return getCustomer(index);
    }

    public int searchCustomer(String key){

        int high = listOfCustomers.numItems;
        int low = 0;
        int mid = (high)/2;

        while(high - low > 0){
            Customer c = (Customer) listOfCustomers.get(mid);

            int compare = key.compareTo(c.getName());
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
        if(low == listOfCustomers.numItems){
            return low;
        }else{
            return low;
        }

    }

}



//Disregard the latter messages  (1:16am)

//Correction to Dilemma below (1:01am Dec 1)if more methods are added to this class they will
//not be deleted

//********************************Tomorrow December 1st this class will most likely be deleted
// the first line in the addShopper method can be integrated into the add shopper method of
//the shopping center class. THis change is because the search is now static and easily called.