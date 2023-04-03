package deque;

import java.util.Iterator;

public class ArrayDeque<T> {
    private T[] items;
    private int size;
    private int startIndex;
    private int endIndex;
    private void copy(T[] temp){
        if (startIndex < endIndex){
            System.arraycopy(items, startIndex, temp, 0, size);
            items = temp;
        }else {
            System.arraycopy(items, startIndex, temp, 0, items.length - startIndex);
            System.arraycopy(items, 0, temp, 0,size - items.length + startIndex);
            items = temp;
        }
    }
    private void addCheckResize() {
        if (size == items.length) {
            T[] temp = (T[]) new Object[size * 4];
            copy(temp);
            startIndex = 0;
            endIndex = size - 1;
        }
    }
    private void removeCheckResize() {
        if (items.length >= 16 && 4 * size < items.length) {
            T[] temp = (T[]) new Object[items.length / 4];
            copy(temp);
            startIndex = 0;
            endIndex = size - 1;
        }
    }
    private void addStartIndex(){
        if (startIndex != 0) {
            startIndex--;
        }else if (size > 0) {
            startIndex = items.length - 1;
        }
    }
    private void removeStartIndex(){
        if (size == 0) {
            startIndex = 0;
            endIndex = -1;
        }else if (startIndex == items.length - 1) {
            startIndex = 0;
        }else {
            startIndex++;
        }
    }
    public ArrayDeque() {
        size = 0;
        startIndex = 0;
        endIndex = -1;
        items = (T[]) new Object[8];
    }
    public void addFirst(T item) {
        addCheckResize();
        addStartIndex();
        items[startIndex] = item;
        if (endIndex == -1){
            endIndex++;
        }
        size++;
    }
    public void addLast(T item) {
        addCheckResize();
        items[size] = item;
        endIndex++;
        size++;
    }
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }
    public int size(){
        return size;
    }
    public void printDeque() {
        if (startIndex == 0){
            for (int i = 0; i < size; i++) {
                System.out.print(items[i] + " ");
            }
            System.out.println();
        }else {
            for (int i = startIndex; i < items.length - startIndex; i++) {
                System.out.print(items[i] + " ");
            }
            for (int i = 0; i < size - items.length + startIndex; i++) {
                System.out.print(items[i] + " ");
            }
            System.out.println();
        }
    }
    public T removeFirst() {
        if (size == 0){
            return null;
        }
        removeCheckResize();
        T temp = items[startIndex];
        items[startIndex] = null;
        size--;
        removeStartIndex();
        return temp;
    }
    public T removeLast() {
        if (size == 0){
            return null;
        }
        removeCheckResize();
        T temp = items[endIndex];
        items[endIndex] = null;
        size--;
        if (endIndex == 0 && size != 0) {
            endIndex = items.length - 1;
        }else if (size == 0) {
            endIndex = -1;
            startIndex = 0;
        }else {
            endIndex--;
        }
        return temp;
    }
    public T get(int index) {
        if (index >= size){
            return null;
        }else if (startIndex + index < items.length) {
            return items[startIndex + index];
        }else {
            return items[startIndex + index - items.length];
        }
    }
    public Iterator<T> iterator(){
        return null;
    }
    public boolean equals(Object o){
       return true;
    }
}

