package erikterwiel.emojify;

import android.util.Log;

import java.util.ArrayList;

public class FixedArrayList<E> extends ArrayList<E> {
    public void enqueue(E item) {
        if (size() == 10) {
            remove(9);
            insert(item);
        } else {
            add(item);
        }
    }
    public void insert(E item) {
        ArrayList<E> temp = new ArrayList<E>();
        temp.add(item);
        for (int i = 0; i < size(); i++) {
            temp.add(get(i));
        }
        clear();
        for (int i = 0; i < temp.size(); i++) {
            add(temp.get(i));
        }
    }
}
