/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private static final int ORIGIN_CAPACITY = 8;

    private Item[] myQueue;
    private int size;
    private int capacity;
    // construct an empty randomized queue
    public RandomizedQueue() {
        capacity = ORIGIN_CAPACITY;
        size = 0;
        myQueue = (Item[]) new Object[capacity];
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        if (size == 0)
            return true;
        return false;
    }

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null)
            throw new IllegalArgumentException();
        if (size == capacity)
            resize(capacity * 2);
        myQueue[size] = item;
        size++;
    }

    // remove and return a random item
    public Item dequeue() {
        if (size == 0)
            throw new NoSuchElementException();
        if (size == capacity / 4 && capacity > ORIGIN_CAPACITY) {
            resize(capacity / 2);
        }
        int index = StdRandom.uniform(size);
        Item randomItem = myQueue[index];
        myQueue[index] = myQueue[size - 1];
        myQueue[size - 1] = null;
        size--;
        return randomItem;
    }

    private void resize(int newCapacity) {
        Item[] copy = (Item[]) new Object[newCapacity];
        capacity = newCapacity;
        for (int i = 0; i < size; i++)
            copy[i] = myQueue[i];
        myQueue = copy;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (size == 0)
            throw new NoSuchElementException();
        int index = StdRandom.uniform(size);
        Item randomItem = myQueue[index];
        return randomItem;
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item> {

        private int[] orderArray;
        private int current;

        public RandomizedQueueIterator() {
            current = 0;
            orderArray = new int[size];
            for (int i = 0; i < size; i++) {
                orderArray[i] = i;
            }
            StdRandom.shuffle(orderArray);
        }

        public boolean hasNext() {
            return current != size;
        }

        public Item next() {
            if (current == size)
                throw new NoSuchElementException();
            int index = orderArray[current];
            Item item = myQueue[index];
            current++;
            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        int n = 50;
        RandomizedQueue<Integer> queue = new RandomizedQueue<Integer>();
        for (int i = 0; i < n; i++)
            queue.enqueue(i);
        for (int a : queue) {
            for (int b : queue)
                StdOut.print(a + "-" + b + " ");
            StdOut.println();
        }

    }


}
