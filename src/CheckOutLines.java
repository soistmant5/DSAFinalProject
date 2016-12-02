/**
 * Created by Joseph on 12/1/2016.
 */
public class CheckOutLines {
    private QueueArrayBased normalCheckout1;
    private QueueArrayBased normalCheckout2;
    private QueueArrayBased expressCheckout;
    private static final int  expressCheckOutLimit = 0;
    private int checkOutOrderCount = 0;

    public CheckOutLines(){
        normalCheckout1 = new QueueArrayBased();
        normalCheckout2 = new QueueArrayBased();
        expressCheckout = new QueueArrayBased();
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

    public QueueArrayBased addToCheckoutLines(Customer customer) {
        QueueArrayBased currentQueue = lengthRegularLines();
        if(customer.getNumItems() <= expressCheckOutLimit){
            //if customer has less than five items EXPRESS LINE
            if(expressCheckout.count >= currentQueue.count){
                //add express customer (less than five items) to normal QUEUES

                currentQueue.enqueue(customer);

            }else{
                //add to express line
                expressCheckout.enqueue(customer);
                return expressCheckout;
                //return "Express CheckOut Line";
            }
        }else{
            //add to shortest
            currentQueue.enqueue(customer);
        }
        return currentQueue;
       //return "Normal CheckOutLine";
    }

    private QueueArrayBased lengthRegularLines(){
        int normal1 = normalCheckout1.count;
        int normal2 = normalCheckout2.count;
        if(normal1 < normal2){
            return normalCheckout1;
        }else{
            return normalCheckout2;
        }
    }

    public QueueArrayBased orderOfCheckout(){
        QueueArrayBased q = null;
        switch(checkOutOrderCount){
            case 0:
                q = normalCheckout1;
                break;

            case 1:
                q = normalCheckout2;
                break;

            case 2:
                q = expressCheckout;
                checkOutOrderCount = -1;
                break;

        }
        checkOutOrderCount++;
        return q;
    }
}
