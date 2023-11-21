package ru.onoregl.bankapi.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.onoregl.bankapi.model.Account;

import java.util.*;

@Repository
public interface AccountDao extends JpaRepository<Account, String> {
    Optional<List<Account>> findByUserid(String userid);
    Optional<Account> update(Account account);
    Optional<Account> create(String userid);
    Optional<Account> findById(String id);
    void deleteById(String id);

}
