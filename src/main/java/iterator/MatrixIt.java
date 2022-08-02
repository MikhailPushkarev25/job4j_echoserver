package iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIt implements Iterator<Integer> {

    private int data[][];
    private int row, column;

    public MatrixIt(int[][] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        boolean rsl = (row < data.length && column < data[row].length);
        if (!rsl == row < data.length) {
            row++;
            column = 0;
            return hasNext();
        }
        return rsl;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }

        if (column == data[row].length) {
            row++;
            column = 0;
        }
        return data[row][column++];
    }
}
