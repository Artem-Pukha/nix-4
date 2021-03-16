package com.spnsolo.calculator;

import com.spnsolo.calculator.factory.FactoryCalc;

import java.math.BigDecimal;

public class PerformerCalculator{

    private static final Calculator CALCULATOR = FactoryCalc.getInstance().getCalcService();

    private PerformerCalculator(){}

    public static BigDecimal sum(String x, String y){ return CALCULATOR.sum(x,y); }

    public static BigDecimal multi(String x, String y) {
        return CALCULATOR.multi(x,y);
    }


    public static BigDecimal subtract(String x, String y) {
        return CALCULATOR.subtract(x,y);
    }


    public static BigDecimal divide(String x, String y)throws ArithmeticException{
        return CALCULATOR.divide(x,y); }
}
