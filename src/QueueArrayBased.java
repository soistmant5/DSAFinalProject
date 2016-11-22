/**
 * Purpose: DSA Lab 6 Problem 1
 * Status: Complete and thoroughly tested
 * Last Updated: 10/12/2016
 * Submitted: 10/13/2016
 * @author Tom Soistmann
 * @version 2016.10.12
 */
public class QueueArrayBased<T> implements QueueInterface<T> {

    protected T[] queue;
    protected int front, back, count;

    public QueueArrayBased() {
        queue = (T[]) new Object[3];
        front = 0;
        back = (queue.length - 1);
        count = 0;
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public boolean isFull() {
        return count == queue.length;
    }

    public void enqueue(T newItem) {
        if (isFull()) {
            T[] tempList = (T[]) new Object[queue.length * 2];
            int index = front;
            for (int i = 0; i < queue.length ; i++) {
                tempList[i] = queue[index % queue.length];
                index++;
            }
            queue = tempList;
            front = 0;
            back = count - 1;
        }
        back = (back + 1) % (queue.length);
        queue[back] = newItem;
        count++;
    }

    public T dequeue() throws QueueException {
        if (!isEmpty()) {
            T temp = queue[front];
            queue[front] = null;
            front = (front + 1) % (queue.length);
            --count;
            return temp;
        } else {
            throw new QueueException("Error on dequeue: Queue is empty.");
        }
    }

    public void dequeueAll() {
        queue = (T[]) new Object[3];
        front = 0;
        back = (queue.length - 1);
        count = 0;
    }

    public T peek() throws QueueException {
        if (!isEmpty()) {
            return queue[front];
        } else {
            throw new QueueException("Error on peek: Queue is empty.");
        }
    }

    public String toString() {
        String result = "";
        int index = front;
        for (int i = 0; i < queue.length ; i++) {
            result += (queue[index % queue.length] + "  ");
            index++;
        }
        return result;
    }
}
