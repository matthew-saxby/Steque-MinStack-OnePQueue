import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MinimumStack<Item extends Comparable<Item>> implements Iterable<Item> {
    private Node<Item> first;  //top of the stack
    private Stack<Item> minStack;  //second stack to keep track of minimum values
    private int size;     // size of the stack
    private int modCount;  //count of modifications

    private static class Node<Item> {
        private Item item;
        private Node<Item> next;
        public Node(Item item) {
            this.item = item;
            this.next = null;
        }
    }

    public MinimumStack() {
        first = null;
        size = 0;
        modCount = 0;
        minStack = new Stack<>(); //creates new stack for minimum values
    }


    // returns the number of items stored
    public int size() {
        return size;
    }

    // returns true iff empty
    public boolean isEmpty() {
        return size==0;
    }

    // push item onto stack
    public void push(Item item) {
        if (item == null) throw new IllegalArgumentException("item cannot be null");

        Node<Item> oldfirst = first;
        first = new Node<>(item);
        first.item = item;
        first.next = oldfirst;

        //add in that if the min stack is empty or if it is less than the top of the minimum stack.
        if(minStack.isEmpty() || item.compareTo(minStack.peek()) <=0 ) {
            minStack.push(item);
        }
        size++;
        modCount++;
    }

    // pop and return the top item
    public Item pop() throws NoSuchElementException {
        if(isEmpty()){
            throw new NoSuchElementException("the stack is empty");
        }
        Item item = first.item;  //save item
        first = first.next;  //move first to the next thing
        size--;//shrink
        modCount++;
        if(minStack.peek() == item){
            minStack.pop();  //if top of the minimum stack equals the value being popped, then pop it too.
        }
        return item;
    }

    // returns the minimum item in constant time
    public Item minimum() throws NoSuchElementException {
        if (isEmpty()){
            throw new NoSuchElementException("empty stack");
        }
        return minStack.peek();
    }

    // returns new Iterator<Item> that iterates over items
    @Override
    public Iterator<Item> iterator() {return new MinimumStackIterator();}

    private class MinimumStackIterator implements Iterator<Item>{
        private Node<Item> current = first;
        private final int finalModCount = modCount;

        public boolean hasNext(){
            if(finalModCount!=modCount){
                throw new ConcurrentModificationException("steque was already modified");
            }
            return current !=null;
        }

        public Item next(){
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

    public static void main(String[] args) {
        MinimumStack<Integer> stack = new MinimumStack<>();

        for (String arg : args) {
            stack.push(Integer.valueOf(arg));

        }
        for (Integer item: stack){
            StdOut.println(item);
        }

        StdOut.println("SIZE: "+stack.size());
        StdOut.println("MIN: "+stack.minimum());

    }
}
