package com.spnsolo;

import com.spnsolo.controller.MainController;
import com.spnsolo.data.LoginInfo;
import com.spnsolo.dto.AccountDto;
import com.spnsolo.dto.CheckedUserDto;
import com.spnsolo.exception.NegativeIndexException;
import com.spnsolo.exception.NonExistedUserException;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;


public class Application {

    private static final Logger logger = Logger.getLogger(Application.class);

    public static void main(String[] args) {
        LoginInfo info = new LoginInfo(args[0], args[1], args[2], args[3]);

        Configuration configuration = new Configuration().configure();
        configuration.setProperty("hibernate.connection.username", info.getUsername());
        configuration.setProperty("hibernate.connection.password", info.getPassword());

        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.openSession();
             Scanner INNER = new Scanner(System.in);
             Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb?useSSL=false&serverTimezone=UTC",
                     info.getUsername(), info.getPassword())) {
            try {
                MainController controller = new MainController(INNER,session,connection);
                session.getTransaction().begin();

                CheckedUserDto user = new CheckedUserDto();
                user.setEmail(info.getEmail());
                user.setId(Long.parseLong(info.getId()));

                controller.checkLogin(user);

                AccountDto account = controller.selectAccount(user);

                System.out.println("Select mod of application: (1) add new transaction; (2) account report;");
                boolean endLoop = false;
                while (!endLoop) {
                    System.out.print("Enter number in \"()\" : ");
                    String YorNo = INNER.nextLine();
                    switch (YorNo) {
                        case "1":
                            logger.info("Selected \"add new transaction\" mod");
                            controller.addTransaction(account);
                            endLoop = true;
                            break;
                        case "2":
                            logger.info("Selected \"account report\" mod");
                            controller.reportAccount(account);
                            endLoop = true;
                            break;
                        default:
                            System.out.println("Invalid operation!");
                    }
                }
                session.getTransaction().commit();
            } catch (NonExistedUserException e) {
                session.getTransaction().rollback();
                System.out.println(e);
            } catch (Exception e) {
                session.getTransaction().rollback();
                logger.trace(e);
            }
        } catch (SQLException e) {
            logger.trace(e);
        }
    }
}
