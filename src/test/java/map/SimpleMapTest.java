package map;

import org.junit.Assert;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleMapTest {

    @Test
    public void whenTestAddMap() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        map.put(1, "Mikhail");
        assertThat(map.get(1), is("Mikhail"));
    }

    @Test
    public void whenTestAddToTwoElement() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        map.put(1, "Mikhail");
        map.put(2, "Roman");
        assertThat(map.get(2), is("Roman"));
    }

    @Test
    public void whenLineArrayHashMap() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        map.put(1, "Mikhail");
        map.put(2, "Mike");
        map.put(3, "Lena");
        map.put(4, "htgd");
        map.put(5, "tygf");
        map.put(6, "fvbn");
        map.put(7, "234");
        map.put(8, "g");
        map.put(9, "gre");
        map.put(10, "6ui");
        map.put(11, "Mikhail");
        map.put(12, "Mike");
        map.put(13, "Lena");
        map.put(14, "htgd");
        map.put(15, "tygf");
        map.put(16, "fvbn");
        map.put(17, "234");
        map.put(18, "g");
        map.put(19, "gre");
        map.put(20, "6ui");
        assertThat(map.get(20), is("6ui"));
    }

    @Test
    public void whenTestRemoveHashMap() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        map.put(1, "Mikhail");
        assertTrue(map.remove(1));
    }

    @Test
    public void whenTestRemoveHashMapNull() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        assertFalse(map.remove(1));
    }

    @Test
    public void whenTestIterator() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        map.put(1, "Mikhail");
        Iterator<Integer> iterator = map.iterator();
        assertThat(iterator.next(), is(1));
    }

    @Test
    public void whenTestHasNext() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        map.put(1, "Mikhail");
        Iterator<Integer> iterator = map.iterator();
        assertTrue(iterator.hasNext());
    }

    @Test(expected = NoSuchElementException.class)
    public void whenErrorNo() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        Iterator<Integer> iterator = map.iterator();
        iterator.next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenErrorConcurrent() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        Iterator<Integer> iterator = map.iterator();
        map.put(1, "M");
        iterator.next();
    }
}