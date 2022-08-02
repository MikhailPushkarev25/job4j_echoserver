package list;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ForwardLinked<T> implements Iterable<T> {

    private Node<T> head;

    public void add(T value) {
        Node<T> newNode = new Node<>(value, null);
        if (head == null) {
            head = newNode;
            return;
        }
        Node<T> tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = newNode;
    }

    public void addFirst(T value) {
        Node<T> newNode = new Node<>(value, null);
        newNode.next = head;
        head = newNode;
    }

    public boolean revert() {
        if (head == null || head.next == null) {
            return false;
        }
        Node<T> prevNode = null;
        Node<T> current = head;
        Node<T> nextNode = null;
        while (current != null) {
            nextNode = current.next;
            current.next = prevNode;
            prevNode = current;
            current = nextNode;
        }
        head = prevNode;
        return true;
    }

    public T deleteFirst() {
        Node<T> node = head;
        if (node == null) {
            throw new NoSuchElementException();
        }
        return unlinkFirst(node);
    }

    public T unlinkFirst(Node<T> f) {
        T element = f.value;
        Node<T> next = f.next;
        f.value = null;
        f.next = null;
        head = next;
        return element;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            Node<T> current = head;
            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {

                if (!hasNext()) {
                    throw new NoSuchElementException();
                }

                T data = current.value;
                current = current.next;
                return data;
            }
        };
    }

    private static class Node<T> {
        T value;
        Node<T> next;

        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }
}
