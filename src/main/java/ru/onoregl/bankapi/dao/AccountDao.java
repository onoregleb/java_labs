package ru.onoregl.bankapi.dao;

import org.springframework.stereotype.Component;
import ru.onoregl.bankapi.model.Account;
import ru.onoregl.bankapi.model.User;

import java.util.*;

@Component
public class AccountDao {
    static Map<String, Account> repository = new HashMap<>();

    public static void updateAccount(Account account) {
        if (repository.containsKey(account.getId())){
            repository.put(account.getId(), account);
        } else{
            throw new RuntimeException("Аккаунт не существует");
        }
    }

    public Account create(String userId) {
        User user = UserDao.findById(userId);

        if (user != null){
            Account account = new Account();
            account.setId(UUID.randomUUID().toString());
            account.setUserid(user.getId());
            repository.put(account.getId(), account);
            return account;
        }
        return null;
    }

    public static Account findById(String id) {
        Account acc = repository.get(id);
        if (acc != null) {
            return acc;
        }
        throw new RuntimeException();
    }

    public static void deleteAccount(String id){
        if (repository.containsKey(id)){
            repository.remove(id);
        }
    }

    public static List<Account> findByUserId(String UserId){
        List<Account> userAccounts = new ArrayList<>();

        for (Account account : repository.values()){
            if (account.getUserid().equals(UserId)){
                userAccounts.add(account);
            }
        }

        return userAccounts;
    }

}