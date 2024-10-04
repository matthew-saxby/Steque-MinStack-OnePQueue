import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Steque<Item> implements Iterable<Item> {
    private Node<Item> first;  //top of the steque
    private Node<Item> last;   // bottom of the seque
    private int size;     // size of the steque
    private int modCount;  //count of modifications

    private static class Node<Item> {
        private Item item;
        private Node<Item> next;

        public Node(Item item) {
            this.item = item;
            this.next = null;
        }
    }

    public Steque() {
        first = null;
        last = null;
        size = 0;
        modCount = 0;
    }

    // returns the number of items stored (returns size variable)
    public int size() {
        return size;
    }

    // returns true iff steque is empty (when size is equal to 0)
    public boolean isEmpty() {
        return size==0;
    }

    // enqueues item to bottom of steque
    public void enqueue(Item item) {
        if (item == null) throw new IllegalArgumentException("item cannot be null");
        Node<Item> newNode = new Node<>(item); // create new node with the item
        if(isEmpty()){
            first = newNode;
            last =  newNode;
        } else{
            last.next = newNode;
            last = newNode;
        }
        size++;
        modCount++;
    }

    // pushes item to top of steque
    public void push(Item item) {
        if (item == null) throw new IllegalArgumentException("item cannot be null");

        Node<Item> oldfirst = first;
        first = new Node<>(item);
        first.item = item;
        first.next = oldfirst;

        if(isEmpty()){
            last = first;
        }
        size++;
        modCount++;
    }

    // pops and returns top item
    public Item pop() throws NoSuchElementException {
        if(isEmpty()){
            throw new NoSuchElementException("the steque is empty");
        }
        Item item = first.item;  //save item
        first = first.next;  //move first to the next thing
        size--;//shrink
        modCount++;
        if(isEmpty()){
            last = null;  //if steque is empy, set the last to null as well.
        }
        return item;
    }

    // returns new Iterator<Item> that iterates over items in steque
    @Override
    public Iterator<Item> iterator() { return new stequeIterator();}

    private class stequeIterator implements Iterator<Item> {
        private Node<Item> current = first;
        private final int finalModCount = modCount;

        public boolean hasNext() {
            if(finalModCount!=modCount){
                throw new ConcurrentModificationException("steque was already modified");
            }
            return (current != null);
        }

        public Item next() {
            if(finalModCount!=modCount){
                throw new ConcurrentModificationException("steque was already modified");
            }
            if(!hasNext()){
                throw new NoSuchElementException("No next item");
            }
            Item item = current.item;
            current = current.next;
            return item;
        }
        public void remove(){
            throw new UnsupportedOperationException("unsupported operation");
        }
    }

    // perform unit testing here
    public static void main(String[] args) throws NoSuchElementException {

        //int steque testing.
        Steque<Integer> steque = new Steque<>();
        for (String arg : args) {
            steque.enqueue(Integer.valueOf(arg));
        }

        for (Integer item : steque) {
            StdOut.println(item);
        }

    }
}