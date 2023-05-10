package deque;
import java.util.Iterator;

public class LinkedListDeque<T> implements Deque<T>, Iterable<T> {
    private int size;
    private final Node sentinel;

    private class Node {
        private Node pre;
        private Node next;
        private T item;
    }

    public LinkedListDeque() {
        this.sentinel = new Node();
        this.size = 0;
        this.sentinel.pre = sentinel;
        this.sentinel.next = sentinel;
        this.sentinel.item = null;
    }

    @Override
    public void addFirst(T item) {
        size++;
        Node newNode = new Node();
        newNode.pre = sentinel;
        newNode.item = item;
        newNode.next = sentinel.next;
        sentinel.next.pre = newNode; //这时候sentinel.next还是原先未改变的。
        sentinel.next = newNode; //改变了sentinel.next
    }

    @Override
    public void addLast(T item) {
        size++;
        Node newNode = new Node();
        newNode.pre = sentinel.pre;
        newNode.item = item;
        newNode.next = sentinel;
        sentinel.pre.next = newNode;
        sentinel.pre = newNode;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        Node curNode = sentinel;
        for (int i = 0; i < size; i++) {
            System.out.print(curNode.next.item + " ");
            curNode = curNode.next;
        }
        System.out.println();
    }

    @Override
    public T removeFirst() {
        if (sentinel.next != sentinel) {
            size--;
            T temp = sentinel.next.item;
            sentinel.next = sentinel.next.next;
            sentinel.next.pre = sentinel;
            return temp;
        } else {
            return null;
        }
    }

    @Override
    public T removeLast() {
        if (sentinel.pre != sentinel) {
            size--;
            T temp = sentinel.pre.item;
            sentinel.pre = sentinel.pre.pre;
            if (size == 0) {
                sentinel.next = sentinel;
            } else {
                sentinel.pre.next = sentinel;
            }

            return temp;
        } else {
            return null;
        }
    }

    @Override
    public T get(int index) {
        Node curNode = sentinel;
        if (index < size) {
            for (int i = 0; i < index + 1; i++) {
                curNode = curNode.next;
            }
            return curNode.item;
        } else {
            return null;
        }
    }

    public T getRecursive(int index) {
        //finalCondition
        if (size == 0 || index > size) {
            return null;
        }
        if (index == 0) {
            return this.sentinel.next.item;
        }

        //general
        LinkedListDeque<T> newList = new LinkedListDeque<>();
        newList.sentinel.next = this.sentinel.next.next;
        newList.size = this.size - 1;
        return newList.getRecursive(index - 1);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Deque && size == ((Deque<?>) o).size()) {
            for (int i = 0; i < size; i++) {
                if (!this.get(i).equals(((Deque<?>) o).get(i))) {
                    return false;
                }
                return true;
            }
        }
        return false;
    }
    @Override
    public Iterator<T> iterator() {
        return new LLDIterator();
    }

    private class LLDIterator implements Iterator<T> {
        private Node pos;
        public LLDIterator() {
            pos = sentinel.next;
        }
        @Override
        public boolean hasNext() {
            return pos != sentinel;
        }

        @Override
        public T next() {
            pos = pos.next;
            return pos.pre.item;
        }
    }
}
