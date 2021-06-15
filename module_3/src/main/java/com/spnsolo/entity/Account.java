package com.spnsolo.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "accounts")
public class Account extends BaseEntity{

    @Column(nullable = false)
    private Double balance;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "account",cascade = CascadeType.REMOVE)
    private List<Transaction> transactions;

    public Account(){
        transactions = new ArrayList<>();
        balance = 0D;
    }
    public Double getBalance() { return balance; }

    public void setBalance(Double balance) { this.balance = balance; }

    public User getUser() { return user; }

    public void setUser(User user) { this.user = user; }

    public List<Transaction> getTransactions() { return transactions; }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
        transaction.setAccount(this);
    }
}
