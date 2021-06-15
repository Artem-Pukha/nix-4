package com.spnsolo.entity;

import javax.persistence.*;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "transactions")
public class Transaction extends BaseEntity{

    @Column(nullable = false)
    private Double value;

    @Column(nullable = false,columnDefinition = "TIMESTAMP")
    private Instant date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    @ManyToMany(cascade = CascadeType.REMOVE)
    @JoinTable(
            joinColumns = @JoinColumn(name = "transaction_id",referencedColumnName = "id")
    )
    private Set<Category> categories;

    public Transaction(){
        categories = new HashSet<>();
        date = Instant.now();
    }

    public Double getValue() { return value; }

    public void setValue(Double value) { this.value = value; }

    public Instant getDate() { return date; }

    public void setDate(Instant date) { this.date = date; }

    public Account getAccount() { return account; }

    public void setAccount(Account account) { this.account = account; }

    public Set<Category> getCategories() { return categories; }

    public void addCategory(Category category) {
        categories.add(category);
        category.getTransactions().add(this);
    }
}
