package com.spnsolo.calculator.impl;

import com.spnsolo.calculator.Calculator;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class OpeningStage  implements Calculator{
    private BigDecimal firstArgument;
    private BigDecimal secondArgument;

    private void convert(String x, String y){
        try {
            this.firstArgument = new BigDecimal(x);
            this.secondArgument = new BigDecimal(y);
        }catch (NumberFormatException e){
            throw new NumberFormatException();
        }
    }
    @Override
    public BigDecimal sum(String x, String y) {
        convert(x,y);
        return this.firstArgument.add(this.secondArgument);
    }

    @Override
    public BigDecimal multi(String x, String y) {
        convert(x,y);

        return this.firstArgument.multiply(this.secondArgument);
    }

    @Override
    public BigDecimal subtract(String x, String y) {
        convert(x,y);

        return this.firstArgument.subtract(this.secondArgument);
    }

    @Override
    public BigDecimal divide(String x, String y) throws ArithmeticException{
        try {
            convert(x,y);
            return this.firstArgument.divide(this.secondArgument,3,RoundingMode.HALF_EVEN);
        }catch (ArithmeticException e){
            throw new ArithmeticException();
        }
    }

}
