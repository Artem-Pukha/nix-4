package com.spnsolo;

import com.spnsolo.io.InputConsoleString;
import com.spnsolo.io.OutputFileString;

public class Main {

    public static void main(String[] args) {

        StringBuffer input = new StringBuffer();

        InputConsoleString reader = new InputConsoleString(input);
        OutputFileString writer = new OutputFileString(reader);
        new Thread(writer).start();

    }
}
