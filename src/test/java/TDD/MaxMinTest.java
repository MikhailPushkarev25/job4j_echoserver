package TDD;

import java.util.Comparator;
import java.util.List;
import org.junit.Test;
import java.util.Arrays;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class MaxMinTest {


    @Test
    public void whenMaxArray() {
        MaxMin maxMin = new MaxMin();
        List<Integer> list = Arrays.asList(
                10, 1, 4, 3, 6, 7, 2, 8, 9
        );
        Integer it = maxMin.max(list, Comparator.comparing(Integer::longValue));
        assertThat(it, is(10));
    }

    @Test
    public void whenMinArray() {
        MaxMin maxMin = new MaxMin();
        List<Integer> list = Arrays.asList(
                10, 1, 4, 3, 6, 7, 2, 8, 9
        );
        Integer it = maxMin.min(list, Comparator.comparing(Integer::longValue));
        assertThat(it, is(1));
    }

}