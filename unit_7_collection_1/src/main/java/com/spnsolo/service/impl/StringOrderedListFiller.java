package com.spnsolo.service.impl;

import com.spnsolo.collection.OrderedList;
import com.spnsolo.service.OrderedListFiller;

import java.util.Scanner;

public class StringOrderedListFiller implements OrderedListFiller {
    private static final Scanner inner = new Scanner(System.in);
    @Override
    public OrderedList<String> fillList() {
        OrderedList<String>strings = new OrderedList<String>();
        System.out.println("Enter your string and press the key \"enter\". If you wanna end the " +
                " entering , type \"e\" and press the key \"enter\"");
        while (true){
            String entered = inner.nextLine();
            if(entered.equals("e")){
                if(strings.isEmpty()){
                    System.out.println("Your list is empty, you must enter something");
                }
                else break;
            }
            else strings.add(entered);
        }
        return strings;
    }

    @Override
    public void addElement(OrderedList list) {
        System.out.println("Enter your string and press the key \"enter\".");
        String entered = inner.nextLine();
        list.add(entered);
    }
}
