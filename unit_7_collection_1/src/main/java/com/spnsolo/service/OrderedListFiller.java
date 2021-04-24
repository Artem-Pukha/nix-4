package com.spnsolo.service;

import com.spnsolo.collection.OrderedList;

public interface OrderedListFiller {
    OrderedList<?> fillList();
    void addElement(OrderedList<?> list);
}
