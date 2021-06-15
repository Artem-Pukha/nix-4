package com.spnsolo.repository.impl;

import com.spnsolo.dto.AccountDto;
import com.spnsolo.dto.CategoryDto;
import com.spnsolo.dto.CheckedUserDto;
import com.spnsolo.dto.TransactionDto;
import com.spnsolo.entity.Account;
import com.spnsolo.entity.Category;
import com.spnsolo.entity.Transaction;
import com.spnsolo.entity.User;
import com.spnsolo.exception.NonExistedUserException;
import com.spnsolo.repository.AccountRepository;
import com.spnsolo.repository.CategoryRepository;
import com.spnsolo.repository.TransactionRepository;
import com.spnsolo.repository.UserRepository;
import org.hibernate.Session;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class MainRepository implements AccountRepository, TransactionRepository, UserRepository, CategoryRepository {

    private Session session;

    public MainRepository(Session session){this.session = session;}
    @Override
    public List<AccountDto> readAllByUserId(Long id) {
        TypedQuery<Account>readAll = session.createQuery("""
                select ac from Account ac where ac.user.id = :id 
                """,Account.class);
        readAll.setParameter("id",id);
        List<Account> accounts = readAll.getResultList();
        List<AccountDto> result = new ArrayList<>();
        this.accountListToDtoList(accounts,result);
        return result;
    }

    @Override
    public void addTransaction(TransactionDto transactionDto) {
        Transaction transaction = new Transaction();
        dtoToEntity(transactionDto, transaction);
        session.persist(transaction);
        transaction.getAccount().setBalance(transactionDto.getAccountDto().getBalance());
    }

    @Override
    public boolean checkUser(CheckedUserDto checkedUserDto) throws NonExistedUserException {
        try {
            TypedQuery<User> readUser = session.createQuery("""
                    from User where id = :id and email = :email
                    """, User.class);
            readUser.setParameter("id", checkedUserDto.getId());
            readUser.setParameter("email", checkedUserDto.getEmail());
            return true;
        }catch (NoResultException e){
            throw new NonExistedUserException(checkedUserDto.getId(), checkedUserDto.getEmail());
        }
    }

    @Override
    public List<CategoryDto> getTypedTransactions(Category.Type type) {
        TypedQuery<Category> getAll = session.createQuery("""
                from Category where type = :type
                """,Category.class);
        getAll.setParameter("type",type);
        List<Category> categories = getAll.getResultList();
        List<CategoryDto> result = new ArrayList<>();
        categoryListToDtoList(categories,result);
        return result;
    }

    private void accountListToDtoList(List<Account> accountList, List<AccountDto> accountDtoList){
        AccountDto accountDto;
        for(Account a:accountList){
            accountDto = new AccountDto();
            entityToDto(a,accountDto);
            accountDtoList.add(accountDto);
        }
    }
    private void categoryListToDtoList(List<Category> categoryList, List<CategoryDto> categoryDtoList){
        CategoryDto categoryDto;
        for(Category c:categoryList){
            categoryDto = new CategoryDto();
            entityToDto(c,categoryDto);
            categoryDtoList.add(categoryDto);
        }
    }
    private void entityToDto(Account account, AccountDto accountDto){
        accountDto.setBalance(account.getBalance());
        accountDto.setId(account.getId());
    }
    private void entityToDto(Category category, CategoryDto categoryDto){
        categoryDto.setId(category.getId());
        categoryDto.setTitle(category.getTitle());
    }
    private void dtoToEntity(TransactionDto dto, Transaction entity) {
        entity.setAccount(session.find(Account.class, dto.getAccountDto().getId()));
        entity.setValue(dto.getValue());
        for (Long id : dto.getCategory_ids()) {
            entity.addCategory(session.find(Category.class, id));
        }
        entity.setAccount(session.find(Account.class,dto.getAccountDto().getId()));
    }
}
