package com.spnsolo.io;

import org.apache.log4j.Logger;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;

public class InputConsoleString implements Runnable {
    private final Logger logger = Logger.getLogger(InputConsoleString.class);

    private final StringBuffer input;
    private boolean changed;

    public InputConsoleString(StringBuffer input){this.input = input;}
    @Override
    public void run() {
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            while (true) {
                System.out.print("Enter string:");
                String line = reader.readLine();

                if(line.contains("quit"))break;
                else{
                    input.append(line);
                    changed = true;
                }
            }
        } catch (IOException e) {
            logger.error(e);
        }
    }

    public Iterable<? extends CharSequence> get() {
        return Collections.singleton(input);
    }

    public boolean isChanged() {
        return changed;
    }
    public void setChanged(boolean status){changed = status;}

}
