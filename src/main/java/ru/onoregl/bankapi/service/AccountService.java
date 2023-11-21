package ru.onoregl.bankapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.onoregl.bankapi.model.Account;
import ru.onoregl.bankapi.model.User;
import ru.onoregl.bankapi.repository.AccountRepository;
import ru.onoregl.bankapi.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final UserRepository userRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository, UserRepository userRepository) {
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
    }

    public Account create(String userId) {
        Optional<User> userOptional = userRepository.findById(userId);

        if (userOptional.isPresent()) {
            User user = userOptional.get();

            Account account = new Account();
            account.setUserid(user.getId());
            return accountRepository.save(account);
        }

        return null;
    }

    public Account findById(Long id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Аккаунт не найден"));
    }

    public void deleteAccount(Long id) {
        accountRepository.deleteById(id);
    }

    public List<Account> findByUserid(String userid) {
        return accountRepository.findByUserid(userid);
    }
}