package edu.hw3;

import java.util.Comparator;
import java.util.TreeMap;

public class Task7 {

    private Task7() {
    }

    public static TreeMap<String, String> createTreeMapWithNullKey() {
        Comparator<String> nullSafeComparator = Comparator.nullsFirst(Comparator.naturalOrder());
        return new TreeMap<>(nullSafeComparator);
    }
}

