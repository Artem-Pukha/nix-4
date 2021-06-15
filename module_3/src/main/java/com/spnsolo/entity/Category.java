package com.spnsolo.entity;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "categories")
public class Category extends BaseEntity{

    @NaturalId
    @Column(nullable = false)
    private String title;

    public enum Type{
        INCOME,
        COST
    }
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Type type;

    @ManyToMany(mappedBy = "categories")
    private Set<Transaction> transactions;

    public Category(){ transactions = new HashSet<>(); }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public Type getType() { return type; }

    public void setType(Type type) { this.type = type; }

    public Set<Transaction> getTransactions() { return transactions; }

    public void addTransaction(Transaction transaction) {
        this.transactions.add(transaction);
        transaction.getCategories().add(this);
    }
}
