package com.spnsolo.calendar.comparator;

import com.spnsolo.calendar.data.MyDate;
import com.spnsolo.calendar.util.ConverterToMilliseconds;

import java.util.Comparator;

public class MyDateComparator implements Comparator<MyDate> {
    @Override
    public int compare(MyDate o1, MyDate o2) {
        long firstDate = ConverterToMilliseconds.dateIntoMilliseconds(o1);
        long secondDate = ConverterToMilliseconds.dateIntoMilliseconds(o2);
        return (int) (secondDate - firstDate);
    }
}
