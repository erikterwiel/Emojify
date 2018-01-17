package erikterwiel.emojify;

import java.util.ArrayList;

public class FixedArrayList<E> extends ArrayList<E> {
    public void enqueue(E item) {
        if (size() == 10) {
            remove(0);
            add(item);
        } else {
            add(item);
        }
    }
}
