package com.spnsolo.algorithm.complexity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class AppSecondTask {
    private static final String file = "files/names.txt";
    public static void run(){
        System.out.println("--------------Second Task--------------");
        Path path = Paths.get(file);
        try {
            ArrayList<String> namesFromFile = (ArrayList<String>) Files.readAllLines(path);
            namesFromFile.forEach(n -> System.out.print(n + " "));
            System.out.println();
            System.out.println(DefinerOfFirstUniqueName.defineFirstUnique(namesFromFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
