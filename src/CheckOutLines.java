/**
 * Purpose: DSA Final Project
 * Status:
 * Last Updated:
 * Submitted:
 * @author Tom Soistmann
 * @author Joseph Messner
 * @version 2016.11.30
 */
public class CheckOutLines {

    private QueueArrayBased normalCheckout1;
    private QueueArrayBased normalCheckout2;
    private QueueArrayBased expressCheckout;
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
        QueueArrayBased shortestNormalCheckout = compareNormalCheckoutLines();

        if (customer.getNumItems() <= 5) {
            //if customer has less than five items EXPRESS LINE
            if (expressCheckout.count >= shortestNormalCheckout.count) {
                //add express customer (less than five items) to normal QUEUES
                shortestNormalCheckout.enqueue(customer);
            } else {
                //add to express line
                expressCheckout.enqueue(customer);
                return expressCheckout;
                //return "Express CheckOut Line";
            }
        } else {
            //add to shortest
            shortestNormalCheckout.enqueue(customer);
        }

        return shortestNormalCheckout;
    }

    private QueueArrayBased compareNormalCheckoutLines() {
        if (normalCheckout1.count < normalCheckout2.count) {
            return normalCheckout1;
        } else {
            return normalCheckout2;
        }
    }

    public QueueArrayBased orderOfCheckout() {
        QueueArrayBased queue = null;

        switch(checkOutOrderCount){
            case 0:
                queue = normalCheckout1;
                break;

            case 1:
                queue = normalCheckout2;
                break;

            case 2:
                queue = expressCheckout;
                checkOutOrderCount = -1;
                break;

        }

        checkOutOrderCount++;
        return queue;
    }
}
