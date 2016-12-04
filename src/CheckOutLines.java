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
    private int checkOutOrderCount;

    public CheckOutLines(){
        normalCheckout1 = new QueueArrayBased();
        normalCheckout2 = new QueueArrayBased();
        expressCheckout = new QueueArrayBased();
        checkOutOrderCount = 0;
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

    public void addToCheckoutLines(Customer customer) {
        QueueArrayBased shortestNormalCheckout = compareNormalCheckoutLines();

        if ((customer.getNumItems() <= 5) && (expressCheckout.count <= shortestNormalCheckout.count)) {
            expressCheckout.enqueue(customer);
        } else {
            //add to shortest
            shortestNormalCheckout.enqueue(customer);
        }
    }

    private QueueArrayBased compareNormalCheckoutLines() {
        if (normalCheckout1.count <= normalCheckout2.count) {
            return normalCheckout1;
        } else {
            return normalCheckout2;
        }
    }

    public QueueArrayBased orderOfCheckout() {
        QueueArrayBased queue = null;

        switch(checkOutOrderCount) {
            case 0:
                if (normalCheckout1.count > 0) {
                    queue = normalCheckout1;
                }
                break;
            case 1:
                if (normalCheckout2.count > 0) {
                    queue = normalCheckout2;
                }
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
