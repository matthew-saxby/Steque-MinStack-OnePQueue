import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class OnePointerQueue<Item> implements Iterable<Item> {
    private Node<Item> back;
    private Node<Item> oldBack;
    private Node<Item> removeItem;
    private int size;
    private int modCount;


    private static class Node<Item> {
        private Item item;
        private Node<Item> next;
        private int set;

        public Node(Item item) {
            this.item = item;
            this.next = null;
        }
    }

    public OnePointerQueue() {
        back = null;
        size = 0;
        modCount = 0;
    }

    // returns the number of items stored
    public int size() {
        return size;
    }

    // returns true iff empty
    public boolean isEmpty() {
        return size == 0;
    }

    // enqueue item to "back" and this is the new pointer for the queue. we change the pointer from
    //the previous "back" to the new back which is the item we enqueued. we also change the next value of this new thing
    // to be what the next value was of the previous back.

    public void enqueue(Item item) {
        if (item == null) throw new IllegalArgumentException("item cannot be null");
        Node<Item> newNode = new Node<>(item); // create new node with the item
        oldBack = back;

        if(isEmpty()){
            //list is empty so make a new node called back
            back = newNode;
            //assign item to be back.item
            back.item = item;
            back.next= back;

        } else{
            //list is not empty so make a variable called old back and assign the current back to it
            //oldBack = back;
            //back is now the new node
            back = newNode;
            //back.item is equal to the item of the new node
            back.item = item;
            //back now points to what old back had been pointing to.
            back.next = oldBack.next;
            //point oldBack to back
            oldBack.next = back;
        }
        size++;
        modCount++;
    }

    // dequeue item from "front". we remove the item and change what the back is pointing to, which is what
    //the thing we just removed had been pointing to.
    public Item dequeue() throws NoSuchElementException {
        if(isEmpty()){
            throw new NoSuchElementException("the queue is empty");
        }
        //create item being removed which equals the item back.next was
        Item item = back.next.item;
        //reassign back.next to equal what back.next had been pointing to.
        back.next = back.next.next;

        //shrink queue
        size--;
        modCount++;
        return item;
    }

    // returns new Iterator<Item> that iterates over items
    @Override
    public Iterator<Item> iterator() {
        return new onePQIterator();
    }

    private class onePQIterator implements Iterator<Item>{
        private Node<Item>current = back.next;
        private final int finalModCount = modCount;
        private int iteratorCount;

        public boolean hasNext() {
            if(finalModCount!=modCount){
                throw new ConcurrentModificationException("queue was already modified");
            }

            return iteratorCount < size;


        }

        public Item next() {
            if(finalModCount!=modCount){
                throw new ConcurrentModificationException("queue was already modified");
            }
            if(!hasNext()){
                throw new NoSuchElementException("No next item");
            }
            Item item = current.item;
            current = current.next;
            iteratorCount++;
            return item;
        }
        public void remove(){
            throw new UnsupportedOperationException("unsupported operation");
        }

    }
    // perform unit testing here
    public static void main(String[] args) {
        OnePointerQueue<String> OPQ = new OnePointerQueue<>();

        for (String arg : args) {
            OPQ.enqueue(String.valueOf(arg));
        }

        for (String item : OPQ) {
            StdOut.println(item);
        }


        StdOut.println("SIZE: "+OPQ.size());
        StdOut.println("dequeued: "+OPQ.dequeue());
        StdOut.println("NEW SIZE: "+OPQ.size());
        StdOut.println("dequeued: "+OPQ.dequeue());

    }
}

//figure out when to stop the circle.

