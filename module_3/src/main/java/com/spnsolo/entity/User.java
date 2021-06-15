package com.spnsolo.entity;

import org.hibernate.annotations.NaturalId;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User extends BaseEntity{
    @NaturalId
    private String email;

    @OneToMany(mappedBy = "user")
    private List<Account> accounts;

    public User(){ accounts = new ArrayList<>(); }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public List<Account> getAccounts() { return accounts; }

    public void addAccount(Account account) {
        accounts.add(account);
        account.setUser(this);
    }
}
