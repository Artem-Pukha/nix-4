package com.spnsolo.util;

import com.spnsolo.entity.Account;
import com.spnsolo.entity.Category;
import com.spnsolo.entity.Transaction;
import com.spnsolo.entity.User;
import org.hibernate.Session;

public class FillerDateBase {
    public static void fil(Session session){
        try {
            session.getTransaction().begin();

            User user = new User();
            user.setEmail("kojima.genius@gmail.com");
            session.persist(user);

            Account account1 = new Account();
            account1.setUser(user);
            session.persist(account1);

            Account account2 = new Account();
            account2.setUser(user);
            session.persist(account2);

            Category category1 = new Category();
            category1.setTitle("Cash back");
            category1.setType(Category.Type.INCOME);
            session.persist(category1);

            Category category2 = new Category();
            category2.setTitle("Salary");
            category2.setType(Category.Type.INCOME);
            session.persist(category2);

            Category category3 = new Category();
            category3.setTitle("Communal payments ");
            category3.setType(Category.Type.COST);
            session.persist(category3);

            Category category4 = new Category();
            category4.setTitle("Taxi");
            category4.setType(Category.Type.COST);
            session.persist(category4);

            Transaction transaction1 = new Transaction();
            transaction1.setValue(10000D);
            transaction1.setAccount(account1);
            transaction1.addCategory(category1);
            session.persist(transaction1);
            account1.setBalance(account1.getBalance() + transaction1.getValue());

            Transaction transaction2 = new Transaction();
            transaction2.setValue(-9000D);
            transaction2.setAccount(account1);
            transaction1.addCategory(category3);
            account1.setBalance(account1.getBalance() + transaction2.getValue());
            session.persist(transaction2);

            session.getTransaction().commit();
        }catch (Exception e){
            session.getTransaction().rollback();
            e.printStackTrace();
        }
    }

}
