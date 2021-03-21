package com.spnsolo.view;

import com.spnsolo.controller.ControllerReverseString;

public class ViewApp {
    public static final ControllerReverseString CONTROLLER = new ControllerReverseString();
    public static void run(){
        CONTROLLER.read();
    }
}
