/**
 * Purpose: DSA Final Project
 * Status: Complete and thoroughly tested
 * Last Updated: 12/06/2016
 * Submitted: 12/06/2016
 * @author Tom Soistmann
 * @author Joseph Messner
 * @version 2016.12.06
 */
public class CheckOutLines {

    private QueueArrayBased regular1;
    private QueueArrayBased regular2;
    private QueueArrayBased express;
    private int checkOutOrderCount;

    /**
     * Constructor for the CheckOutLines class.
     * Instantiates each of the three QueueArrayBased collections.
     */
    public CheckOutLines() {
        regular1 = new QueueArrayBased();
        regular2 = new QueueArrayBased();
        express = new QueueArrayBased();
        checkOutOrderCount = 0;
    }

    /**
     * Accessor method to return the QueueArrayBased collection stored in the regular1 data field.
     *
     * @return A stored instance of a QueueArrayBased collection
     */
    public QueueArrayBased getNormalCheckout1() {
        return regular1;
    }

    /**
     * Accessor method to return the QueueArrayBasedCollection stored in the regular2 data field.
     *
     * @return A stored instance of a QueueArrayBased collection
     */
    public QueueArrayBased getNormalCheckout2() {
        return regular2;
    }

    /**
     * Accessor method to return the QueueArrayBasedCollection stored in the express data field.
     *
     * @return A stored instance of a QueueArrayBased collection
     */
    public QueueArrayBased getExpressCheckout() {
        return express;
    }

    /**
     * Adds a customer who is finished shopping to the appropriate QueueArrayBased collection.
     * If the customer that is passed in via parameter has less than or equal to 4 items, and the number of customers
     * already in the express queue is less than twice the length of the shortest regular checkout line, then
     * the customer is placed into the express line.
     *
     * Otherwise, they are placed into the shortest regular checkout line.
     *
     * @param customer The Customer object to be inserted into a QueueArrayBased collection
     */
    public void addToCheckoutLines(Customer customer) {
        QueueArrayBased shortestRegularCheckout = compareNormalCheckoutLines();

        if (customer.getNumItems() <= 4) {
            if (express.count > (2 * shortestRegularCheckout.count)) {
                shortestRegularCheckout.enqueue(customer);
            } else {
                express.enqueue(customer);
            }
        } else {
            shortestRegularCheckout.enqueue(customer);
        }
    }

    /**
     * Comparison method to find which of the two regular checkout lines has the least amount
     * of customers in it.
     *
     * @return The QueueArrayBased collection of the shortest regular checkout line
     */
    private QueueArrayBased compareNormalCheckoutLines() {
        if (regular1.count <= regular2.count) {
            return regular1;
        } else {
            return regular2;
        }
    }

    /**
     * Determines which of the three checkouts will checkout customers first.
     *
     * @param checkout The checkout specified by the user to be used first
     */
    public void checkOutStart(String checkout) {
        switch(checkout) {
            case "regular1":
                checkOutOrderCount = 1;
                break;
            case "regular2":
                checkOutOrderCount = 2;
                break;
        }
    }

    /**
     * Determines which checkout line is next when checking customers out.
     * If multiple checkout lines are populated, the lines take turns. Otherwise, those which are populated
     * are favored.
     *
     * @return The next QueueArrayBased collection to remove a customer from
     */
    public QueueArrayBased orderOfCheckout() {
        QueueArrayBased queueToDequeue = null;
        boolean inserted = false;
        int condition = 0;

        while (condition < 3 && !inserted) {
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
                        checkOutOrderCount = -1;
                    }
                    break;
            }
            checkOutOrderCount++;
            condition++;

            if (checkOutOrderCount > 2) {
                checkOutOrderCount = 0;
            }
        }

        return queueToDequeue;
    }
}

