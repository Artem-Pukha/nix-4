package com.spnsolo.console.helper.factory;

import com.spnsolo.console.helper.IOConsole;
import org.reflections.Reflections;

import java.util.Set;

public class FactoryConsole {

    private static FactoryConsole instance;
    private Reflections reflections;
    private Set<Class<? extends IOConsole>> consoles;

    private FactoryConsole() {
        reflections = new Reflections("com.spnsolo.console.helper");
        consoles = reflections.getSubTypesOf(IOConsole.class);
    }

    public static FactoryConsole getInstance() {
        if (instance == null) {
            instance = new FactoryConsole();
        }
        return instance;
    }

    public IOConsole getCansoleService() throws RuntimeException{
        for (Class<? extends IOConsole> console : consoles) {
            if (!console.isAnnotationPresent(Deprecated.class)) {
                try {
                    return console.getDeclaredConstructor().newInstance();
                } catch (Exception e) {
                    throw new RuntimeException();
                }
            }
        }
        throw new RuntimeException();
    }
}
