package list;

import java.util.*;

public class SimpleArrayList<T> implements List<T> {

    private T[] container;
    private int size;
    private int modCount;


    public SimpleArrayList(int capacity) {
        this.container = (T[]) new Object[capacity];
    }

    @Override
    public void add(T value) {
        modCount++;
            expansion();
        container[size++] = value;
    }

    @Override
    public T set(int index, T newValue) {
        Objects.checkIndex(index, size);
        expansion();
        T oldElement = container[index];
        container[index] = newValue;
        return oldElement;
    }

    @Override
    public T remove(int index) {
        Objects.checkIndex(index, size);
        size--;
        modCount++;
        T rem = container[index];
        System.arraycopy(container, index + 1, container, index, size - index);
        container[size] = null;
        return rem;
    }

    @Override
    public T get(int index) {
        Objects.checkIndex(index, size);
        modCount++;
        return container[index];
    }

    @Override
    public int size() {
        return size;
    }

    private void expansion() {
       if (container.length == 0) {
           container = Arrays.copyOf(container, 10);
       }
       if (size == container.length) {
           container = Arrays.copyOf(container, container.length * 2);
       }
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int count = 0;
            final int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                return count < size;
            }

            @Override
            public T next() {

                if (!hasNext()) {
                    throw new NoSuchElementException();
                }

                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }

                return container[count++];
            }
        };
    }
}
