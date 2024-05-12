import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] array;
    private int size;

    // construct an empty randomized queue
    public RandomizedQueue() {
        array = (Item[]) new Object[2];
        size = 0;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null)
            throw new IllegalArgumentException("Input item should not be null");
        if (size == array.length)
            resize(2 * size);
        array[size++] = item;
    }

    private void resize(int capacity) {
        int i;
        Item[] copy = (Item[]) new Object[capacity];
        for (i = 0; i < size; i++)
            copy[i] = array[i];
        array = copy;
    }

    // remove and return a random item
    public Item dequeue() {
        int index;
        if (size == 0)
            throw new java.util.NoSuchElementException("The Deque is empty");
        index = StdRandom.uniformInt(size);
        Item retItem = array[index];
        array[index] = array[--size];
        array[size] = null;
        if (size > 0 && size <= array.length / 4)
            resize(array.length / 2);
        return retItem;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        int index;
        if (size == 0)
            throw new java.util.NoSuchElementException("The Deque is empty");
        index = StdRandom.uniformInt(size);
        return array[index];
    }


    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new ArrayInterator();
    }

    private class ArrayInterator implements Iterator<Item> {
        int curr = 0;
        Item[] copy;

        public ArrayInterator() {
            int i;
            copy = (Item[]) new Object[size];
            for (i = 0; i < size; i++)
                copy[i] = array[i];
            StdRandom.shuffle(copy);
        }

        public boolean hasNext() {
            return curr != size;
        }

        public Item next() {
            if (curr == size)
                throw new java.util.NoSuchElementException("No next");
            Item retItem = copy[curr];
            curr++;
            return retItem;
        }

        public void remove() {
            throw new UnsupportedOperationException(
                    "This Deque implementation doesn't support remove in iterator");
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<String> rq = new RandomizedQueue<>();
        for (int i = 0; i < 18; i++) {
            rq.enqueue("A" + i);
        }
        System.out.println("first iterator");
        for (String s : rq) {
            System.out.print(s + " ");
        }
        System.out.println();
        System.out.println("second iterator");
        for (String s : rq) {
            System.out.print(s + " ");
        }
        System.out.println();
        for (int i = 0; i < 18; i++) {
            System.out.print("deque ");
            System.out.print(rq.dequeue());
            System.out.println(". remain " + rq.size() + " elements. now capacity ");
        }
    }

}
