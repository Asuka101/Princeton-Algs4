import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {
    private Node front;
    private Node back;
    private int size;

    private class Node {
        Item value;
        Node prev;
        Node next;

        public Node(Item input) {
            value = input;
            prev = null;
            next = null;
        }
    }

    // construct an empty deque
    public Deque() {
        front = null;
        back = null;
        size = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null)
            throw new IllegalArgumentException("Input item should not be null");
        Node curr = new Node(item);
        if (size == 0) {
            front = curr;
            back = curr;
        }
        else {
            curr.next = front;
            front.prev = curr;
            front = curr;
        }
        size++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null)
            throw new IllegalArgumentException("Input item should not be null");
        Node curr = new Node(item);
        if (size == 0) {
            back = curr;
            front = curr;
        }
        else {
            back.next = curr;
            curr.prev = back;
            back = curr;
        }
        size++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (size == 0)
            throw new java.util.NoSuchElementException("The Deque is empty");
        Item oldfront = front.value;
        front = front.next;
        if (front != null)
            front.prev = null;
        else 
            back = null;
        size--;
        return oldfront;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (size == 0)
            throw new java.util.NoSuchElementException("The Deque is empty");
        Item oldback = back.value;
        back = back.prev;
        if (back != null)
            back.next = null;
        else 
            front = null;
        size--;
        return oldback;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new ListInterator();
    }

    private class ListInterator implements Iterator<Item> {
        private Node curr = front;

        public boolean hasNext() {
            return curr != null;
        }

        public Item next() {
            if (curr == null)
                throw new java.util.NoSuchElementException();
            Item item = curr.value;
            curr = curr.next;
            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException(
                    "This Deque implementation doesn't support remove in iterator");
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<String> dq = new Deque<>();
        for (int i = 0; i < 5; i++) {
            dq.addFirst("A" + i);
        }
        for (int i = 0; i < 5; i++) {
            dq.addLast("B" + i);
        }
        for (String s : dq) {
            System.out.println(s);
        }
        System.out.println("dq has " + dq.size() + " elements in total");
        for (int i = 0; i < 5; i++) {
            System.out.println(dq.removeFirst());
            System.out.println(dq.removeLast());
            System.out.println(dq.size());
        }
    }
}
