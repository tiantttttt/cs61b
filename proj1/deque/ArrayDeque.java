package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T> {
    private T[] items;
    private int size;
    private int startIndex;
    private int endIndex;
    private void copy(T[] temp) {
        if (startIndex < endIndex) {
            System.arraycopy(items, startIndex, temp, 0, size);
            items = temp;
        } else {
            System.arraycopy(items, startIndex, temp, 0, items.length - startIndex);
            System.arraycopy(items, 0, temp, items.length - startIndex, endIndex + 1);
            items = temp;
        }
        startIndex = 0;
        endIndex = size - 1;
    }
    private void addCheckResize() {
        if (size == items.length) {
            T[] temp = (T[]) new Object[size * 4];
            copy(temp);
        }
    }
    private void removeCheckResize() {
        if (items.length >= 16 && 4 * size < items.length) {
            T[] temp = (T[]) new Object[items.length / 4];
            copy(temp);
        }
    }
    private void addStartIndex() {
        if (startIndex != 0) {
            startIndex--;
        } else if (size > 0) {
            startIndex = items.length - 1;
        }
    }
    private void removeFirstChangeIndex() {
        if (size == 0) {
            startIndex = 0;
            endIndex = -1;
        } else if (startIndex == items.length - 1) {
            startIndex = 0;
        } else {
            startIndex++;
        }
    }
    private void removeLastChangeIndex() {
        if (endIndex == 0 && size != 0) {
            endIndex = items.length - 1;
        } else if (size == 0) {
            endIndex = -1;
            startIndex = 0;
        } else {
            endIndex--;
        }
    }
    public ArrayDeque() {
        size = 0;
        startIndex = 0;
        endIndex = -1;
        items = (T[]) new Object[8];
    }
    @Override
    public void addFirst(T item) {
        addCheckResize();
        addStartIndex();
        items[startIndex] = item;
        if (endIndex == -1) {
            endIndex++;
        }
        size++;
    }
    @Override

    public void addLast(T item) {
        addCheckResize();
        if (endIndex < items.length - 1) {
            endIndex++;
            items[endIndex] = item;
        } else {
            endIndex = 0;
            items[endIndex] = item;
        }
        size++;
    }
    @Override
    public int size() {
        return size;
    }
    @Override
    public void printDeque() {
        if (startIndex == 0) {
            for (int i = 0; i < size; i++) {
                System.out.print(items[i] + " ");
            }
            System.out.println();
        } else {
            for (int i = startIndex; i < items.length - startIndex; i++) {
                System.out.print(items[i] + " ");
            }
            for (int i = 0; i < size - items.length + startIndex; i++) {
                System.out.print(items[i] + " ");
            }
            System.out.println();
        }
    }
    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        removeCheckResize();
        T temp = items[startIndex];
        items[startIndex] = null;
        size--;
        removeFirstChangeIndex();
        return temp;
    }
    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        removeCheckResize();
        T temp = items[endIndex];
        items[endIndex] = null;
        size--;
        removeLastChangeIndex();
        return temp;
    }
    @Override
    public T get(int index) {
        if (index >= size) {
            return null;
        } else if (startIndex + index < items.length) {
            return items[startIndex + index];
        } else {
            return items[startIndex + index - items.length];
        }
    }
    public boolean equals(Object o) {
        if (o instanceof Deque && size == ((Deque<?>) o).size()) {
            for (int i = 0; i < size; i++) {
                if (!this.get(i).equals(((Deque<?>) o).get(i))) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
    @Override
    public Iterator<T> iterator() {
        return new ADIterator();
    }
    private class ADIterator implements Iterator<T> {
        public int pos;
        public ADIterator() {
            pos = 0;
        }
        @Override
        public boolean hasNext() {
            return pos < size;
        }

        @Override
        public T next() {
            pos++;
            return items[pos - 1];
        }
    }
}

