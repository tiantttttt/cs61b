package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Deque<T>, Iterable<T> {
    private class Node {
        private T item;
        private Node next;
        private Node pre;

        private  Node(Node p, T i, Node n) {
            this.item = i;
            this.next = n;
            this.pre = p;
        }
    }
    private int size;
    private Node sentinel;

    public LinkedListDeque() {
        size = 0;
        sentinel = new Node(sentinel, null, sentinel);
        sentinel.pre = sentinel;
        sentinel.next = sentinel;
    }
    @Override
    public void addFirst(T item) {
        size++;
        Node newNode = new Node(sentinel, item, sentinel.next);
        sentinel.next.pre = newNode; //这时候sentinel.next还是原先未改变的。
        sentinel.next = newNode; //改变了sentinel.next
    }
    @Override
    public void addLast(T item) {
        size++;
        Node newNode = new Node(sentinel.pre, item, sentinel);
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

    public boolean equals(Object o) {
        if (o instanceof Deque && size == ((Deque<?>) o).size()) {
            for (int i = 0; i < size; i++){
                if (!this.get(i).equals(((Deque<?>) o).get(i))) {
                    return false;
                }
                return true;
            }
        }
        return false;
    }
    public Iterator<T> iterator() {
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator<T> {
        private Node node;

        LinkedListIterator() {
            this.node = sentinel.next;
        }

        public boolean hasNext() {
            return this.node != sentinel;
        }

        public T next() {
            T ret = this.node.item;
            this.node = this.node.next;
            return ret;
        }

    }
}
