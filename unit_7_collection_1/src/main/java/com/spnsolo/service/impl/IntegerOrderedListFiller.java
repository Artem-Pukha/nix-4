package com.spnsolo.service.impl;

import com.spnsolo.collection.OrderedList;
import com.spnsolo.service.OrderedListFiller;

import java.util.Scanner;

public class IntegerOrderedListFiller implements OrderedListFiller {
    private static final Scanner inner = new Scanner(System.in);
    @Override
    public OrderedList<Integer> fillList(){
        while (true) {
            try {
                OrderedList<Integer> integers = new OrderedList<Integer>();
                System.out.println("Enter your integer and press the key \"enter\". If you wanna end the " +
                        " entering , type \"e\" and press the key \"enter\"");
                while (true) {
                    String str = inner.nextLine();
                    if (str.equals("e")) {
                        if (integers.isEmpty()) {
                            System.out.println("Your list is empty, you must enter something");
                        } else break;
                    } else {
                        Integer entered = Integer.parseInt(str);
                        integers.add(entered);
                    }
                }
                return integers;
            } catch (NumberFormatException e) {
                System.out.println("Invalid data");
            }
        }
    }

    @Override
    public void addElement(OrderedList list) throws NumberFormatException{
        System.out.println("Enter your integer and press the key \"enter\".");
        Integer entered = inner.nextInt();
        list.add(entered);
    }
}
