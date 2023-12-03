package ru.onoregl.bankapi.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import ru.onoregl.bankapi.controller.UserNotFoundException;
import ru.onoregl.bankapi.model.Card;
import ru.onoregl.bankapi.model.User;

import java.util.*;

@Repository
public interface CardDao extends JpaRepository<Card, String> {
    List<Card> findByUserid(String userid);

    Optional<Card> findByCardId(String cardId);

    void deleteByCardId(String cardId);
}

