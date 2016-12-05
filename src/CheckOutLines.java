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

    private QueueArrayBased regular1;
    private QueueArrayBased regular2;
    private QueueArrayBased express;
    private int checkOutOrderCount;


    public CheckOutLines() {
        regular1 = new QueueArrayBased();
        regular2 = new QueueArrayBased();
        express = new QueueArrayBased();
        checkOutOrderCount = 0;
    }

    public QueueArrayBased getNormalCheckout1() {
        return regular1;
    }

    public QueueArrayBased getNormalCheckout2() {
        return regular2;
    }

    public QueueArrayBased getExpressCheckout() {
        return express;
    }

    public void addToCheckoutLines(Customer customer) {
        QueueArrayBased shortestNormalCheckout = compareNormalCheckoutLines();

        if ((customer.getNumItems() <= 4) && (express.count <= shortestNormalCheckout.count)) {
            express.enqueue(customer);
        } else {
            //add to shortest
            shortestNormalCheckout.enqueue(customer);
        }
    }

    private QueueArrayBased compareNormalCheckoutLines() {
        if (regular1.count <= regular2.count) {
            return regular1;
        } else {
            return regular2;
        }
    }

    public void checkOutStart(String register) {
        switch(register) {
            case "regular1":
                checkOutOrderCount = 1;
                break;
            case "regular2":
                checkOutOrderCount = 2;
                break;
        }
    }

    public QueueArrayBased orderOfCheckout() {
        QueueArrayBased queueToDequeue = null;
        boolean inserted = false;

        while (checkOutOrderCount < 3 && !inserted) {
            switch (checkOutOrderCount) {
                case 0:
                    if (express.count > 0) {
                        inserted = true;
                        queueToDequeue = express;
                    }
                    break;
                case 1:
                    if (regular1.count > 0) {
                        inserted = true;
                        queueToDequeue = regular1;
                    }
                    break;
                case 2:
                    if (regular2.count > 0) {
                        inserted = true;
                        queueToDequeue = regular2;
                    }
                    break;
            }
            checkOutOrderCount++;
        }

        if (checkOutOrderCount > 2) {
            checkOutOrderCount = 0;
        }

        return queueToDequeue;
    }
}
