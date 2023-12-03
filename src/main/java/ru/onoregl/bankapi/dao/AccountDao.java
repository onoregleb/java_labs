package ru.onoregl.bankapi.dao;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.onoregl.bankapi.model.Account;

import java.util.*;

@Repository
public interface AccountDao extends JpaRepository<Account, String> {
    List<Account> findByUserid(String userid);
    @Modifying
    @Transactional
    @Query("UPDATE Account a SET a.balance = :balance WHERE a.id = :id")
    void updateBalanceById(@Param("id") String id, @Param("balance") double balance);
    Optional<Account> findById(String id);
    void deleteById(String id);


}
