package com.spnsolo.controller;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import com.spnsolo.data.TableCsv;
import com.spnsolo.dto.*;
import com.spnsolo.entity.Category;
import com.spnsolo.exception.IllegalTransactionException;
import com.spnsolo.exception.NegativeIndexException;
import com.spnsolo.exception.NonExistedUserException;
import com.spnsolo.exception.ZeroTransactionException;
import com.spnsolo.io.OutputCsv;
import com.spnsolo.repository.TransactionRepository;
import com.spnsolo.repository.impl.MainRepository;
import com.spnsolo.repository.impl.ReportDAO;
import com.spnsolo.service.AccountService;
import com.spnsolo.service.CategoryService;
import com.spnsolo.service.TransactionService;
import com.spnsolo.service.UserService;
import com.spnsolo.service.impl.AccountServiceImpl;
import com.spnsolo.service.impl.CategoryServiceImpl;
import com.spnsolo.service.impl.TransactionServiceImpl;
import com.spnsolo.service.impl.UserServiceImpl;
import com.spnsolo.util.FileResourcesUtil;
import org.apache.log4j.Logger;
import org.hibernate.Session;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import java.util.Scanner;

public class MainController {
    private final Scanner INNER;
    private final Session session;
    private final Connection connection;
    private final Logger logger = Logger.getLogger(MainController.class);

    public MainController(Scanner scanner,Session session,Connection connection){
        INNER = scanner;
        this.session = session;
        this.connection = connection;
    }

    public boolean checkLogin(CheckedUserDto user) throws NonExistedUserException {
        UserService service = new UserServiceImpl(session);
        return service.checkUser(user);
    }
    public AccountDto selectAccount(CheckedUserDto user){
        System.out.println("Select your accounts:");
        AccountService service = new AccountServiceImpl(session);

        List<AccountDto> accounts = service.readAllByUserId(user.getId());
        for (int i = 0; i < accounts.size(); i++) {
            System.out.println("(" + (i+1) + ") - id = " + accounts.get(i).getId() + " balance = " +
                    accounts.get(i).getBalance() + " ;");
        }
        while (true){
            try {
                System.out.print("Enter number in \"()\" : ");
                String buffer = INNER.nextLine();
                int index = Integer.parseInt(buffer);
                if(index < 0)throw new NegativeIndexException();
                logger.info("User's login");
                return accounts.get(index-1);
            }catch (NumberFormatException e){
                System.out.println("You've entered invalid data!");
            }catch (NegativeIndexException e){
                System.out.println(e);
            }catch (IndexOutOfBoundsException e){
                System.out.println("You've entered non existed index!");
            }
        }
    }
    public void addTransaction(AccountDto accountDto) throws IllegalTransactionException {
        TransactionService transactionService = new TransactionServiceImpl(session);
        CategoryService categoryService = new CategoryServiceImpl(session);
        TransactionDto newTransaction = new TransactionDto();
        newTransaction.setAccountDto(accountDto);

        System.out.print("Input value of transaction (positive - income , negative - cost, 0 - unavailable): ");
        double value;
        while (true){
            try{
                value = Double.parseDouble(INNER.nextLine());
                if(value == 0)throw new ZeroTransactionException();
                newTransaction.setValue(value);
                break;
            }catch (NumberFormatException e){
                System.out.println("Invalid data");
            }catch (ZeroTransactionException e){
                System.out.println(e);
            }
        }
        logger.info("Entered value to transaction " + value);
        List<CategoryDto> categoryDtos;

        if(value < 0)categoryDtos = categoryService.getTypedTransactions(Category.Type.COST);
        else categoryDtos = categoryService.getTypedTransactions(Category.Type.INCOME);

        System.out.println("Select transaction's category:");
        for (int i = 0; i < categoryDtos.size(); i++) {
            System.out.println("(" + i + ") " + categoryDtos.get(i));
        }
        boolean endLoop = false;
        while (!endLoop){
            try {
                System.out.print("Enter number in \"()\" : ");
                int index = Integer.parseInt(INNER.nextLine());
                if(index < 0)throw new NegativeIndexException();
                newTransaction.addCategory_ids(categoryDtos.get(index).getId());
                while(true) {
                    System.out.println("Add one more category?(yes - y , no - n)");
                    String YorNo = INNER.nextLine();
                    if (YorNo.equals("n")) {
                        endLoop = true;
                        break;
                    }
                    if(YorNo.equals("y")){break;}
                }
            }catch (NumberFormatException e){
                System.out.println("You've entered invalid data!");
            }catch (NegativeIndexException e){
                System.out.println(e);
            }catch (IndexOutOfBoundsException e){
                System.out.println("You've entered non existed index!");
            }
        }
        logger.info("Selected transaction's categories");
        transactionService.addTransaction(newTransaction);
    }

    public void reportAccount(AccountDto accountDto){
        System.out.print("""
                Select the period of report:
                (1)All time
                (2)Year
                (3)Month
                (4)Week
                (5)Day
                """);
        ReportDAO.Period period = ReportDAO.Period.ALL;
        boolean endLoop = false;
        while (!endLoop) {
            System.out.print("Enter number in \"()\" : ");
            String YorNo = INNER.nextLine();
            switch (YorNo) {
                case "1" -> endLoop = true;
                case "2" -> {
                    period = ReportDAO.Period.YEAR;
                    endLoop = true;
                }
                case "3" -> {
                    period = ReportDAO.Period.MONTH;
                    endLoop = true;
                }
                case "4" -> {
                    period = ReportDAO.Period.WEEK;
                    endLoop = true;
                }
                case "5" -> {
                    period = ReportDAO.Period.DAY;
                    endLoop = true;
                }
                default -> System.out.println("Invalid index!");
            }
        }
        logger.info("Selected period for report");
        ReportDAO dao = new ReportDAO(connection);
        List<TransactionOutput> outputList = dao.getAllTransactionsForPeriod(accountDto,period);
        String path = "output.csv";
        try (CSVWriter writer = new CSVWriter(new FileWriter(path))){
            OutputCsv output = new OutputCsv(writer);
            output.setCsv(outputList,accountDto);
            logger.info("Report has been created");
        } catch (IOException e) {
            logger.trace(e);
        }

    }

}
