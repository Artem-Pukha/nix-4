package com.spnsolo.calculator.factory;

import com.spnsolo.calculator.Calculator;

import org.reflections.Reflections;
import java.util.Set;

public class FactoryCalc {

    private static FactoryCalc instance;
    private Reflections reflections;
    private Set<Class<? extends Calculator>> calculators;

    private FactoryCalc() {
        reflections = new Reflections("com.spnsolo.calculator");
        calculators = reflections.getSubTypesOf(Calculator.class);
    }

    public static FactoryCalc getInstance() {
        if (instance == null) {
            instance = new FactoryCalc();
        }
        return instance;
    }

    public Calculator getCalcService() throws RuntimeException{
        for (Class<? extends Calculator> calcService : calculators) {
            if (!calcService.isAnnotationPresent(Deprecated.class)) {
                try {
                    return calcService.getDeclaredConstructor().newInstance();
                } catch (Exception e) {
                    throw new RuntimeException();
                }
            }
        }
        throw new RuntimeException();
    }
}
