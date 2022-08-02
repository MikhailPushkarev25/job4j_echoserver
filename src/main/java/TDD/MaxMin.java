package TDD;

import java.util.Comparator;
import java.util.List;

public class MaxMin {

    public <T> T findBy(List<T> value, Comparator<T> comparator) {
        T rsl = value.get(0);
        for (int i = 0; i < value.size(); i++) {
            rsl = comparator.compare(rsl, value.get(i)) > 0 ? rsl : value.get(i);
        }
        return rsl;
    }

    public <T> T max(List<T> value, Comparator<T> comparator) {
        return findBy(value, comparator);
    }

    public <T> T min(List<T> value, Comparator<T> comparator) {
        return findBy(value, comparator.reversed());
    }
}
