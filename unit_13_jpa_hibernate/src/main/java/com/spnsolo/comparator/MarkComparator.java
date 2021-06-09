package com.spnsolo.comparator;

import com.spnsolo.entity.Mark;

import java.util.Comparator;

public class MarkComparator implements Comparator<Mark> {
    @Override
    public int compare(Mark o1, Mark o2) {
        return o1.getValue() - o2.getValue();
    }
}
