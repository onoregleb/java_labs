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
    Optional<List<Card>> findCardsByUserId(String userId);
    Optional<User> update(User user);
    Optional<Card> create(String AccountId, String userId);
    Optional<Card> findById(String cardId);
    void delete(String cardId);

}

