package com.spnsolo.service.impl;

import com.spnsolo.collection.OrderedList;
import com.spnsolo.data.TestUser;
import com.spnsolo.service.OrderedListFiller;

import java.util.Scanner;

public class TestUserOrderedListFiller implements OrderedListFiller {
    private static final Scanner inner = new Scanner(System.in);
    @Override
    public OrderedList<TestUser> fillList() {
        OrderedList<TestUser>users = new OrderedList<TestUser>();
        System.out.println("Enter a name of user and press the key \"enter\". If you wanna end the " +
                " entering , type \"e\" and press the key \"enter\"");
        while (true) {
            String name = inner.nextLine();
            TestUser user = new TestUser();
            if (name.equals("e")) {
                if (users.isEmpty()) {
                    System.out.println("Your list is empty, you must enter something");
                } else break;
            } else {
                user.setName(name);
                users.add(user);
            }
        }
        return users;
    }

    @Override
    public void addElement(OrderedList list) {
        System.out.println("Enter a name of user  and press the key \"enter\".");
        String name = inner.nextLine();
        TestUser user = new TestUser();
        user.setName(name);
        list.add(user);
    }
}
