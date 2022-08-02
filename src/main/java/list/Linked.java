package list;

public interface Linked<E> extends Iterable<E> {

    void add(E value);
    E get(int index);
}
