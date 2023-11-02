package edu.hw3;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class Task8 {

    private Task8() {
    }

    public static class BackwardIterator<T> implements Iterator<T> {

        private final List<T> list;
        private int currentIndex;

        public BackwardIterator(List<T> list) {
            this.list = list;
            this.currentIndex = list.size() - 1;
        }

        @Override
        public boolean hasNext() {
            return currentIndex >= 0;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return list.get(currentIndex--);
        }
    }
}
