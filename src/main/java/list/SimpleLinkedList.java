package list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleLinkedList<E> implements Linked<E> {

    private int size = 0;
    private int modCount = 0;
    private Node<E> first;
    private Node<E> last;

    @Override
    public void add(E value) {
        Node<E> l = last;
        Node<E> newNode = new Node<>(l, value, null);
        last = newNode;
        if (l == null) {
            first = newNode;
        } else {
            l.next = newNode;
        }
        size++;
        modCount++;
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        Node<E> f = first;
        while (index > 0){
            f = f.next;
            index--;
        }
        return f.element;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {

            Node<E> current = first;
            int expectedModeCount = modCount;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }

                if (expectedModeCount != modCount) {
                    throw new ConcurrentModificationException();
                }

                E data = current.element;
                current = current.next;
                return data;
            }
        };
    }

    private static class Node<E> {
        E element;
        Node<E> next;
        Node<E> prev;

        public Node(Node<E> prev, E element, Node<E> next) {
            this.element = element;
            this.next = next;
            this.prev = prev;
        }
    }
}
