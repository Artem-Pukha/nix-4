package com.spnsolo.algorithm.complexity;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class TestOfSecondTask {
    private static final String FILE = "files/names.txt";
    public static void run(){
        System.out.println();
        System.out.println("/////////Test/////////");
        try {
            Path path = Paths.get(FILE);
            ArrayList<String> namesFromFile = (ArrayList<String>) Files.readAllLines(path);
            System.out.println("The list of names from file \"files/names.txt\"");
            namesFromFile.forEach(n -> System.out.print(n + " "));
            System.out.println();
            System.out.println();
            System.out.println("First unique name from list: ");
            int index = DefinerOfFirstUniqueName.defineFirstUnique(namesFromFile);
            System.out.println(namesFromFile.get(index) + " (with an index " + index +")");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
