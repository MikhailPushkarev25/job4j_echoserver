package map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;
    private int capacity = 8;
    private int count = 0;
    private int modCount = 0;


    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        if (count + 1 >= LOAD_FACTOR * table.length) {
            expand();
        }
        int hashCode = hashCode(key.hashCode());
        int index = indexFor(hashCode);
        MapEntry<K, V> entry = table[index];
        if (entry == null) {
            table[index] = new MapEntry<>(key, value);
        } else {
           table[index].key = (K) new MapEntry<K, V>(key, value);
           return true;
        }
        count++;
        modCount++;
        return false;
    }

    private int hashCode(int hashCode) {
        return (hashCode ^ (hashCode >>> 31));
    }

    private int indexFor(int hash) {
        return Math.abs(hash) % table.length;
    }

    private void expand() {
        MapEntry<K, V>[] oldTable = table;
        table = new MapEntry[oldTable.length * 2];
        count = 0;
        for (MapEntry<K, V> entry : oldTable) {
            if (entry != null) {
                while (entry.value != null) {
                    put(entry.key, entry.value);
                    break;
                }
            }
        }
        count--;
        modCount++;
    }

    @Override
    public V get(K key) {
        int index = indexFor(key.hashCode());
        MapEntry<K, V> entry = table[index];
        count++;
        return entry.value;
    }

    @Override
    public boolean remove(K key) {
        int index = indexFor(key.hashCode());
        if (table[index] == null) {
            return false;
        }
        MapEntry<K, V> entry = table[index];
        while (entry.value != null) {
            if (key.equals(entry.key)) {
                entry.value = null;
                return true;
            }
        }
        count--;
        modCount++;
        return false;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {
            int expectedModCount = modCount;
            int sizeValue = 0;
            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                while (sizeValue < table.length && table[sizeValue] == null) {
                    sizeValue++;
                }
                return sizeValue < table.length;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[sizeValue++].key;
            }
        };
    }

    private static class MapEntry<K, V> {
        K key;
        V value;


        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
