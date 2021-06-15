package com.spnsolo.dto;

import java.time.Instant;
import java.util.List;

public class TransactionOutput {
    private Instant date;
    private Double value;

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}
