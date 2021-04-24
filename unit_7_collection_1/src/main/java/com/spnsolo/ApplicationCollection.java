package com.spnsolo;

import com.spnsolo.collection.OrderedList;
import com.spnsolo.data.TestUser;
import com.spnsolo.service.OrderedListFiller;
import com.spnsolo.service.impl.IntegerOrderedListFiller;
import com.spnsolo.service.impl.StringOrderedListFiller;
import com.spnsolo.service.impl.TestUserOrderedListFiller;

import java.util.Scanner;

public class ApplicationCollection {
    private static final Scanner inner = new Scanner(System.in);
    private static OrderedList<?> list;
    private static OrderedListFiller filler;
    public static void run(){
        try {
            System.out.println("------------Collection \"Ordered List(Sort entered value)\"------------");
            System.out.println();
            boolean endApp = false;
            while (!endApp) {
                System.out.print("What type of information do you want to enter in list?\n" +
                        "String(1),\nIntegers(2),\nTest class \"User\"(3)\n");
                selectType();
                list = filler.fillList();
                selectOperation();
                System.out.println("Finish the program (Yes - y/No- n)?");
                String YorNo = inner.nextLine();
                if (YorNo.equals("y")) {
                    System.out.println("Thank you for using!");
                    endApp = true;
                }
            }
            inner.close();
        }catch (NumberFormatException e){
            System.out.println("You enter invalid data");
        }
    }
    private static void selectType(){
        boolean endLoop = true;
        do{
            System.out.print("Select the type: ");
            String index = inner.nextLine();
            switch (index) {
                case "1":
                    filler = new StringOrderedListFiller();
                    break;
                case "2":
                    filler = new IntegerOrderedListFiller();
                    break;
                case "3":
                    filler = new TestUserOrderedListFiller();
                    break;
                default:
                    endLoop = false;
                    System.out.println("Incorrect symbol!");
            }
        }while (!endLoop);
    }
    private static void selectOperation(){
        boolean endLoop = false;
        do{
            System.out.println("""
                    Select the operation below:
                    Print whole list(1)
                    Remove element by index(2)
                    Add element to list(3)
                    Clear the list(4)
                    Contains all elements(Return \"true\" if all entered value in the your list")(5)
                    Add all elements to list(Add entered elements to your list)(6)
                    End operations(e)
                    """);
            System.out.print("The operation: ");
            String index = inner.nextLine();
            switch (index) {
                case "1":
                    printWholeList();
                    break;
                case "2":
                    removeElementByIndex();
                    break;
                case "3":
                    addElementToList();
                    break;
                case "4":
                    clearTheList();
                    break;
                case "5":
                    containsAllElements();
                    break;
                case "6":
                    addAllElements();
                    break;
                case "e":
                    endLoop = true;
                    break;
                default:
                    System.out.println("Incorrect symbol!");
            }
        }while (!endLoop);
    }
    private static void printWholeList(){
        if(list.size()==0) System.out.println("List is empty");
        else {
            for (int i = 0; i < list.size(); i++) {
                System.out.println(i + ": " + list.get(i));
            }
        }
    }
    private static void removeElementByIndex(){
        while (true) {
            try {
                printWholeList();
                System.out.print("Enter the index(number before a value of your list) from " + 0 + "-" + (list.size() - 1) + ": ");
                String index = inner.nextLine();
                list.remove(Integer.parseInt(index));
                break;
            } catch (NumberFormatException e) {
                System.out.println("You entered incorrect value!");
            }
        }
    }
    private static void addElementToList(){
        filler.addElement(list);
    }

    private static void clearTheList(){
        list.clear();
    }
    private static void containsAllElements(){
        OrderedList container = filler.fillList();
        System.out.println(list.containsAll(container));
    }
    private static void addAllElements(){
        OrderedList container = filler.fillList();
        list.addAll(container);
    }





}

