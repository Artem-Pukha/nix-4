package com.spnsolo.date.to.string;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class AppFirstTask {
    private static final String file = "files/date.txt";
    public static void run(){
        System.out.println("---------------------First task---------------------");
        System.out.println("Given list of dates(string notation):");

        Path path = Paths.get(file);
        try{
            List<String> dateInString = Files.readAllLines(path);
            dateInString.forEach(System.out::println);

            System.out.println("The list of formatted dates with correct first form:");

            List<String> formatted = new ArrayList<>();
            for(String s : dateInString){
                s = Converter.changeAvailableFormattedDate(s);
                if(!s.equals(""))formatted.add(s);
            }

            formatted.forEach(System.out::println);
            System.out.println("----------------------------------------------------");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
