package com.spnsolo.console.helper;

import com.spnsolo.console.helper.factory.FactoryConsole;

public class ConsoleHelper {
    private static final IOConsole CONSOLE = FactoryConsole.getInstance().getCansoleService();

    private ConsoleHelper(){}

    public static void cout(Object o){
        CONSOLE.cout(o);
    }
    public static void coutl(Object o){
        CONSOLE.coutl(o);
    }
    public static void endl(){CONSOLE.endl();}
    public static String cin(){
        return CONSOLE.cin();
    }

}
