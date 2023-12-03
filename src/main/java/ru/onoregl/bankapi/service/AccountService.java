package ru.onoregl.bankapi.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.onoregl.bankapi.controller.UserNotFoundException;
import ru.onoregl.bankapi.dao.AccountDao;
import ru.onoregl.bankapi.dao.UserDao;
import ru.onoregl.bankapi.dto.CreateAccountDto;
import ru.onoregl.bankapi.model.Account;


import java.util.List;
import java.util.UUID;

@Service
public class AccountService {
    @Autowired
    private AccountDao dao;

    public AccountService(AccountDao dao) {
        this.dao = dao;
    }

    public Account createAccountForUser(CreateAccountDto createAccountDto) {
        Account client = new Account();
        client.setId(UUID.randomUUID().toString());
        client.setUserid(createAccountDto.getUserid());
        client.setBalance(createAccountDto.getBalance());
        dao.save(client);
        return client;
    }

    public void deleteAccount(String AccountId){
        dao.deleteById(AccountId);
    }

    @Transactional
    public Account changeAccountBalance(String accountId, double amount) {
        Account account = dao.findById(accountId).orElse(null);

        if (account != null) {
            double newBalance = account.getBalance() + amount;
            account.setBalance(newBalance);
            dao.updateBalanceById(accountId, newBalance);
        }

        return account;
    }

    public Account findById(String accountId) {
        Account account = dao.findById(accountId).orElse(null);

        if (account == null){
            throw new UserNotFoundException("Account not found for accountId: " + accountId);
        }

        return account;
    }

    public List<Account> findByUserId(String userId) {
        List <Account> accounts = dao.findByUserid(userId);

        if (accounts == null){
            throw new UserNotFoundException("Accounts not found for userId: " + userId);
        }

        return accounts;
    }
}