package erikterwiel.emojify;

public class Queue {

    public Queue(int capacity) {
        this.capacity = capacity;
    }

    private Node first;
    private Node last;
    private int n;
    private int capacity;

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return n;
    }

    public void enqueue(String item) {
        if (n == capacity) {
            first = first.getNext();
            Node oldLast = last;
            last = new Node();
            last.setItem(item);
            last.setNext(null);
            oldLast.setNext(last);
        } else {
            Node oldLast = last;
            last = new Node();
            last.setItem(item);
            last.setNext(null);
            if (isEmpty()) {
                first = last;
            } else {
                oldLast.setNext(last);
            }
            n += 1;
        }
    }
}
