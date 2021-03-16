package com.spnsolo.calculator;

import java.math.BigDecimal;

public interface Calculator {

    public BigDecimal sum(String x, String y);
    public BigDecimal multi(String x, String y);
    public BigDecimal subtract(String x, String y);
    public BigDecimal divide(String x, String y);

}
