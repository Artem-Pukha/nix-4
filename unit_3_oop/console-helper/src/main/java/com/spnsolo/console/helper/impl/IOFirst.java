package com.spnsolo.console.helper.impl;

import com.spnsolo.console.helper.IOConsole;

import java.io.IOException;

public class IOFirst implements IOConsole {
    @Override
    public void cout(Object o) {
        System.out.print(o);
    }

    @Override
    public void coutl(Object o) {
        System.out.println(o);
    }

    @Override
    public void endl() {
        System.out.println();
    }

    @Override
    public String cin() {
        int inChar;
        String s = "";
        try {
            inChar = System.in.read();
            while (System.in.available() > 0) {
                s += (char) inChar;
                inChar = System.in.read();
            }
        } catch (IOException e) {
            System.out.println("Ошибка");
        }
        return s;
    }
}
