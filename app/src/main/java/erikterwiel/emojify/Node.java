package erikterwiel.emojify;

public class Node {
    private String mItem;
    private Node mNext;

    public String getItem() {
        return mItem;
    }

    public void setItem(String item) {
        mItem = item;
    }

    public Node getNext() {
        return mNext;
    }

    public void setNext(Node next) {
        mNext = next;
    }
}
