package com.spnsolo.date.to.string;

import java.util.ArrayList;
import java.util.List;

public class AppFirstTask {
    private static final List<String> dateInString = new ArrayList<>();
    static {
        dateInString.add("2020-04/05");
        dateInString.add("2021-04-05");
        dateInString.add("2020/12/01");
        dateInString.add("01/02/2000");
        dateInString.add("12/04/2001");
        dateInString.add("27/12/2001");
        dateInString.add("01-6-20");
        dateInString.add("10 march 01");
        dateInString.add("94 1 april");
        dateInString.add("05-march-2021");
        dateInString.add("05-01-2013");
    }
    public static void run(){
        System.out.println("---------------------First task---------------------");
        System.out.println("Given list of dates(string notation):");

        dateInString.forEach(System.out::println);

        System.out.println("The list of formatted dates with correct first form:");

        List<String> formatted = new ArrayList<>();
        for(String s : dateInString){
            s = Converter.changeAvailableFormattedDate(s);
            if(!s.equals(""))formatted.add(s);
        }

        formatted.forEach(System.out::println);
        System.out.println("----------------------------------------------------");
    }
}
