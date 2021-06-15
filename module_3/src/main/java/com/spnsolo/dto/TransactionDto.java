package com.spnsolo.dto;

import java.util.HashSet;
import java.util.Set;


public class TransactionDto {
    private Double value;
    private AccountDto accountDto;
    private Set<Long> category_ids;

    public TransactionDto(){ category_ids = new HashSet<>(); }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public AccountDto getAccountDto() {
        return accountDto;
    }

    public void setAccountDto(AccountDto accountDto) {
        this.accountDto = accountDto;
    }

    public Set<Long> getCategory_ids() {
        return category_ids;
    }

    public void addCategory_ids(Long category_id) {
        this.category_ids.add(category_id);

    }
}
